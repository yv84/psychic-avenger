package me.yv84.springlayout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */
@Controller
public class App {

    @RequestMapping("/")
    public ModelAndView test() {
        return new ModelAndView("index");
    }
}
