package me.yv84.springlayout.repository.jdbc.impl;

import com.mchange.v2.c3p0.C3P0ProxyStatement;
import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.jdbc.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

    static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
    
    @Autowired
    private DataSource dataSource;

    public List<Account> getAll() {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM USER";

        List<Account> result = new ArrayList<Account>();
        
        try {
            con = dataSource.getConnection();
            logger.debug("connect: " + con);
            pstm = con.prepareStatement(sql);
            logger.debug("prepareStatement: "
                + printC3p0Statement((C3P0ProxyStatement) pstm));

            rs = pstm.executeQuery();
            logger.info("executeQuery: " + rs);
            
            while (rs.next()) {
                Account account = new Account(
                    new Long(rs.getInt("ID")), rs.getString("USERNAME"));
                result.add(account);
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


    public Account get(Long id) {

        logger.debug("id: " + id);
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM USER WHERE USER.ID = ?";

        Account result = null;

        try {
            con = dataSource.getConnection();
            logger.debug("connect: " + con);
            
            pstm = con.prepareStatement(sql);
            pstm.setLong(1, id);
            
            logger.debug("prepareStatement: "
                + printC3p0Statement((C3P0ProxyStatement) pstm));
            rs = pstm.executeQuery();
            logger.info("executeQuery: " + rs.getStatement());

            
            if (rs.next()) {
                Account account = new Account(
                    new Long(rs.getInt("ID")), rs.getString("USERNAME"));
                result = account;
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

    private static String printC3p0Statement( C3P0ProxyStatement stmt )
            throws NoSuchMethodException, IllegalAccessException, SQLException, InvocationTargetException {
        java.lang.reflect.Method m = java.io.PrintStream.class.getMethod("println", new Class[]{Object.class});
        return (String) stmt.rawStatementOperation(m, System.out, new Object[]{ C3P0ProxyStatement.RAW_STATEMENT });
    }
    
    
    
}
