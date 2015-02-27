package me.yv84.springlayout.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */
@Controller
public class IndexController {
    static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public ModelAndView index() {
        logger.info("index.page");
        return new ModelAndView("index");
    }

//    @RequestMapping("/**")
//    public ModelAndView pageNotFound() {
//        logger.warn("404.page");
//        return new ModelAndView("404");
//    }
}
