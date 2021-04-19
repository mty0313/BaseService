package top.mty.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.mty.bean.Person;
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
public class PersonController {

    @Resource
    IPersonService personService;

    @PostMapping("/getAll")
    public R getAllData(@RequestBody Map<String, Object> params) {
        List<Person> data = personService.list();
        return R.ok(params.get("queryUrl"), data);
    }

}
