package me.yv84.springlayout.controller;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.service.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;


@Controller
public class IndexController extends BaseController {
    static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AccountManager accountManager;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.info("index.page");
        ModelAndView modelAndView = new ModelAndView();
        List<Account> rsList = accountManager.getAll();
        modelAndView.addObject("accounts", rsList);

        Iterator<Account> resultIterator = rsList.iterator();
        while (resultIterator.hasNext()) {
            Account rs = resultIterator.next();
            System.out.println(rs.getId() + ", " + rs.getUsername());
        }

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView index(@PathVariable Long id,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        if (id == 0) { // 404 if /0?
            logger.info("404.page");
            throw new ResourceNotFoundException(id);
        } else {

            {
                Account rs = accountManager.get(id);
                System.out.println(rs.getId() + ", " + rs.getUsername());
            }

            logger.info("account id: " + id);
            modelAndView.setViewName("index");
            return modelAndView;
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        logger.info("login");
        return redirectTo("/");
    }

    @ResponseBody
    @RequestMapping(value="/account/{id}",
        method=RequestMethod.DELETE,
        produces = "application/json")
    public Account accountDelete(@PathVariable Long id,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("delete: " + id);
        Account account = accountManager.get(id);
        if (account != null) {
            accountManager.delete(account);
        }
        return account;
    }

    @ResponseBody
    @RequestMapping(value="/account/{id}",
        method=RequestMethod.PUT,
        produces = "application/json")
    public Account accountUpdate(@PathVariable Long id, @RequestParam(value="username") String username,
            HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        logger.info("" + id + " / " +  username);
        Account account = accountManager.get(id);
        if (account != null) {
            accountManager.update(new Account(id, username));
        }
        return account;
    }

    @ResponseBody
    @RequestMapping(value="/account/",
        method=RequestMethod.POST,
        produces = "application/json")
    public Account accountInsert(@RequestParam(value="username") String username,
                                 HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        logger.info("" + username);
        Long id = accountManager.add(new Account(username));
        return new Account(id, username);
    }
    
}
