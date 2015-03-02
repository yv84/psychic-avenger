package me.yv84.springlayout.repository.jdbc.impl;

import me.yv84.springlayout.repository.jdbc.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

    static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
    
    @Autowired
    private DataSource dataSource;

    public List<Object> getAll(Long id) {

        logger.info("id: " + id);
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM USER";

        List<Object> result = new ArrayList<Object>();
        
        try {
            con = dataSource.getConnection();
            logger.info("connect: " + con);
            pstm = con.prepareStatement(sql);
            logger.info("prepareStatement: " + pstm);
            rs = pstm.executeQuery();
            logger.info("executeQuery: " + rs);
            
            while (rs.next()) {
                Object[] resultArray = {rs.getInt("ID"), rs.getString("USERNAME")};
                result.add(resultArray);
            }

            rs.close();
            
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
        
        return result;
    };
}
