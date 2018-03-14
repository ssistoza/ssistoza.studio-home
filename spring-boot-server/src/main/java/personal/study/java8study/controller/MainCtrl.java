package personal.study.java8study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainCtrl {

    @RequestMapping(method = RequestMethod.GET)
    public String getSPA() {
        return "index.html";
    }

    @GetMapping("/syno")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        return new ModelAndView("forward:https://ssistoza.studio:5001", model);
    }

}
