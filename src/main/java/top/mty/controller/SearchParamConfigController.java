package top.mty.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.mty.entity.SearchParamConfig;
import top.mty.service.ISearchParamConfigService;
import top.mty.service.impl.SearchParamConfigServiceImpl;
import top.mty.utils.R;
import top.mty.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * es查询参数配置 前端控制器
 * </p>
 *
 * @author mty
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/mty/search-param-config")
public class SearchParamConfigController {

    @Resource
    ISearchParamConfigService searchParamConfigService;
    
    @Resource
    RedisUtil redisUtil;

    @ApiOperation(value = "保存搜索配置信息到Redis", httpMethod = "POST")
    @PostMapping("/save-param-config-redis")
    public R saveParamConfig2Redis() {
        List<SearchParamConfig> configList = searchParamConfigService.list();
        List<String> queryUrls = configList.stream().distinct().map(SearchParamConfig::getUrl).collect(Collectors.toList());
        queryUrls.forEach(url -> {
            List data = redisUtil.hgetAll(url);
            if (data.size() > 0) {
                String queryUrl = JSON.parseObject(JSON.toJSONString(data.get(0))).get("url").toString();
                // 把redis中的内容清除
                redisUtil.del(queryUrl);
            }
        });
        configList.forEach(config -> redisUtil.hset(config.getUrl(),
                config.getFieldNameAlias(),
                JSON.parseObject(JSON.toJSONString(config))));
        return R.ok();
    }
}
