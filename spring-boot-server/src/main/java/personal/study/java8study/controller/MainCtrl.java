package personal.study.java8study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainCtrl {

    @RequestMapping(method = RequestMethod.GET)
    public String getSPA() {
        return "index.html";
    }

    /**
     * Redirects to Synology Login Screen.
     * @param model Placeholder.
     * @return ModelAndView : Redirects user to Synology URL.
     */
    @GetMapping("/syno")
    public ModelAndView redirectSynoStation(ModelMap model) {
        return new ModelAndView("redirect:https://ssistoza.studio:5001/", model);
    }

    @GetMapping("/jenkins")
    public ModelAndView redirectJenkins(ModelMap model) {
        return new ModelAndView("redirect:https://ssistoza.studio:8088/", model);
    }
}
