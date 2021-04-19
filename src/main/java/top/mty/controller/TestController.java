package top.mty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author mty
 * @Date 2021/4/16
 * @Description test controller
 */
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/testController/{val}")
    public String testController(@PathVariable String val) {
        logger.info("get val: {}", val);
        return val;
    }

}
