package top.mty.controller;


import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.mty.entity.Person;
import top.mty.service.IPersonService;
import top.mty.utils.R;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * person 前端控制器
 * </p>
 *
 * @author mty
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/person")
@Api(tags = "person controller")
public class PersonController {

    @Resource
    IPersonService personService;

    @PostMapping("/get-all")
    public R getAllData(@RequestBody Map<String, Object> params) {
        List<Person> data = personService.list();
        return R.ok(params.get("queryUrl"), data);
    }

    /**
     *
     * @param params
     * @return
     */
    @PostMapping("/get-list")
    public R getData(@RequestBody Map<String, Object> params) {
        // params非空校验
        for (String key: params.keySet()) {
            if (StringUtils.isEmpty(params.get(key).toString())) {
                params.remove(key);
            }
        }
        return personService.getList(params);
    }

    @PostMapping("/save-all")
    public R saveAllData(@RequestBody Map<String, Object> params) {
        return personService.saveOrUpdateEs(params);
    }

}
