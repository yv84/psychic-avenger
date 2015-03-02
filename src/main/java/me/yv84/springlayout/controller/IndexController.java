package me.yv84.springlayout.controller;

import me.yv84.springlayout.service.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;


@Controller
public class IndexController {
    static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AccountManager accountManager;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.info("index.page");
        return new ModelAndView("index");
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView index(@PathVariable Long id,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (id == 0) { // 404 if /0?
            logger.info("404.page");
            throw new ResourceNotFoundException(id);
        } else {

            List<Object> rsList = accountManager.getAll();
            Iterator<Object> resultIterator = rsList.iterator();
            while (resultIterator.hasNext()) {
                Object[] rs = (Object[]) resultIterator.next();
                System.out.println(rs[0] + ", " + rs[1]);
            }
            {
                Object[] rs = accountManager.get(id);
                System.out.println(rs[0] + ", " + rs[1]);
            }

            logger.info("index.page: " + id);
            return new ModelAndView("index");
        }
    }
}
