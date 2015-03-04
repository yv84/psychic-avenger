package me.yv84.springlayout.controller;


public abstract class BaseController {

    private static final String SPRING_REDIRECT = "redirect:";
    
    protected String redirectTo(String viewToRedirect) {
        return SPRING_REDIRECT + viewToRedirect;
    }

}