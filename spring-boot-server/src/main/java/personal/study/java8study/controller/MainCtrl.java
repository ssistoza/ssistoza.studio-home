package personal.study.java8study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainCtrl {

    @RequestMapping(method = RequestMethod.GET)
    public String getSPA() {
        return "index.html";
    }

}
