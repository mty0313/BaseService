package top.mty.Controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.mty.Bean.Person;
import top.mty.Service.IPersonService;
import top.mty.Utils.R;

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
    public R getAllData() {
        List<Person> data = personService.list();
        return R.ok(data);
    }

}
