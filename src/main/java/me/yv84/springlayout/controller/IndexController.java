package me.yv84.springlayout.controller;

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
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Hello world!
 *
 */
@Controller
public class IndexController {
    static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DataSource c3p0DataSource;

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

            logger.info("id: " + id);
            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM USER";
            try {
                con = c3p0DataSource.getConnection();
                logger.info("connect: " + con);
                pstm = con.prepareStatement(sql);
                logger.info("prepareStatement: " + pstm);
                rs = pstm.executeQuery();
                logger.info("executeQuery: " + rs);
                while (rs.next()){
                    System.out.println(
                        rs.getInt("ID")
                        + ", "
                        + rs.getString("USERNAME")
                    );
                }
                rs.close();

                System.out.println(rs);
            }catch (Exception e){

                e.printStackTrace();
                throw new RuntimeException(e);

            }finally{

                try{

                    //Closing all the opened resources
                    if (rs!=null) rs.close();
                    if (pstm!=null) pstm.close();
                    if (con!=null) con.close();

                }catch (Exception e){

                    e.printStackTrace();
                    throw new RuntimeException(e);

                }

            }
            logger.info("index.page: " + id);
            return new ModelAndView("index");
        }
    }
}
