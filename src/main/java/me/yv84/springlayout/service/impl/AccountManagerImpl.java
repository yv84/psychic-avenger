package me.yv84.springlayout.service.impl;

import me.yv84.springlayout.model.Account;
import me.yv84.springlayout.repository.AccountDao;
import me.yv84.springlayout.repository.fixtures.ChartData;
import me.yv84.springlayout.repository.mybatis.AAccountMapper;
import me.yv84.springlayout.repository.mybatis.AccountMapper;
import me.yv84.springlayout.repository.mybatis.DAccountMapper;
import me.yv84.springlayout.service.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "accountManager")
public class AccountManagerImpl implements AccountManager {

    static final Logger LOGGER = LoggerFactory.getLogger(ChartData.class);

    @Autowired
    private AccountDao jdbcAccountDao;
    
    @Autowired
    private AccountDao jpaAccountDao;

    @Autowired
    private AccountDao hibernateAccountDao;

    @Autowired
    private ChartData fixtureChartData;

    private AccountMapper mybatisDao;
    public AccountMapper getMuserMapper() {
        return mybatisDao;
    }
    @Autowired
    public void setMuserMapper(AccountMapper mybatisDao) {
        this.mybatisDao = mybatisDao;
    }
    
    private AAccountMapper aMybatisDao;
    public AAccountMapper getAuserMapper() {
        return aMybatisDao;
    }
    @Autowired
    public void setAuserMapper(AAccountMapper aMybatisDao) {
        this.aMybatisDao = aMybatisDao;
    }

    private DAccountMapper dMybatisDao;
    public DAccountMapper getDuserMapper() {
        return dMybatisDao;
    }
    @Autowired
    public void setDuserMapper(DAccountMapper dMybatisDao) {
        this.dMybatisDao = dMybatisDao;
    }
    
    
    @Override
    public List<Account> getAll() {
        return dMybatisDao.selectAllAccounts();
    }

    @Override
    public Account get(Long id) {
        return dMybatisDao.accountWithFullnameById(id);
    }

    @Override
    public List<Account> getList(Long id) {
        return dMybatisDao.accountWithAddressById(id);
    }

    @Override
    public Long add(Account account) {
        return dMybatisDao.insertAccount(account);
    }

    @Override
    public void update(Account account) {
        dMybatisDao.updateAccount(account);
    }

    @Override
    public void delete(Account account) {
        dMybatisDao.deleteAccount(account);
    }
    
    
    
    // -------------------------------------
    public List<Object> getRevenue30Day() {
        final Integer dayCount = -30; // last 30 days chart

        List<Object> revenues = fixtureChartData.getRevenue30Day();
        LOGGER.info(revenues.toString());
        List<Object> revenueMap = new ArrayList<Object>();

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        Calendar calDaysOfMonth = Calendar.getInstance();
        Date today = new Date();
        calDaysOfMonth.setTime(today);
        calDaysOfMonth.add(Calendar.DAY_OF_MONTH, dayCount);
        Integer d = dayCount;

        Calendar calSql = Calendar.getInstance();

        for(int i=0; i<revenues.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("revenue", ((Object[])revenues.get(i))[0]);
            try {
                calSql.setTime((Date)formatter.parse(
                    ((Object[]) revenues.get(i))[1].toString()
                ));
                for(; d<0; d++, calDaysOfMonth.add(Calendar.DAY_OF_MONTH, 1)) {
                    if (calSql.get(Calendar.YEAR) ==
                        calDaysOfMonth.get(Calendar.YEAR)
                        &&  calSql.get(Calendar.DAY_OF_YEAR) ==
                        calDaysOfMonth.get(Calendar.DAY_OF_YEAR)) {
                        d++;
                        calDaysOfMonth.add(Calendar.DAY_OF_MONTH, 1);
                        break;
                    } else {
                        Map <String, Object> mapOfNull = new HashMap<String, Object>();
                        mapOfNull.put("revenue", 0);
                        mapOfNull.put("created",
                            formatter.format(calDaysOfMonth.getTime()));
                        revenueMap.add(mapOfNull);
                    }
                }
                map.put("created", ((Object[])revenues.get(i))[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            revenueMap.add(map);
        }
        for(; d<0; d++, calDaysOfMonth.add(Calendar.DAY_OF_MONTH, 1)) {
            Map <String, Object> mapOfNull = new HashMap<String, Object>();
            mapOfNull.put("revenue", 0);
            mapOfNull.put("created",
                formatter.format(calDaysOfMonth.getTime()));
            revenueMap.add(mapOfNull);
        }
        return revenueMap;
    }
    
    
}
