package top.mty.service.impl;

import com.alibaba.fastjson.JSON;
import top.mty.entity.SearchParamConfig;
import top.mty.mapper.SearchParamConfigMapper;
import top.mty.service.ISearchParamConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mty.utils.RedisUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * es查询参数配置 服务实现类
 * </p>
 *
 * @author mty
 * @since 2021-04-20
 */
@Service
public class SearchParamConfigServiceImpl extends ServiceImpl<SearchParamConfigMapper, SearchParamConfig> implements ISearchParamConfigService {

    @Resource
    RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        List<SearchParamConfig> configList = list();
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
    }


}
