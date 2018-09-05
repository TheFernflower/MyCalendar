package Calendar.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ferney on 02.04.2018.
 */

@Controller
public class HelloController {
    @RequestMapping("/test")
    @ResponseBody
    String hello() {
        return "Hello World!";
    }

}