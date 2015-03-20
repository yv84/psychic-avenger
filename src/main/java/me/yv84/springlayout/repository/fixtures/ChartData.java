package me.yv84.springlayout.repository.fixtures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yv84 on 3/20/15.
 */
@Repository("fixtureChartData")
public class ChartData {

    static final Logger LOGGER = LoggerFactory.getLogger(ChartData.class);
    
    public static final List<Object> getRevenue30Day() {
        
        int[] revenues = new int[] {1580,294216,51920,14948,16016,56664,87120,173425,153816,56736,19812,121693,219840};
        String[] dates = new String[] {
            "2015-02-20 15:16:33.0",
            "2015-02-22 15:16:33.0",
            "2015-02-23 15:16:33.0",
            "2015-03-04 15:16:33.0",
            "2015-03-08 15:16:33.0",
            "2015-03-09 15:16:33.0",
            "2015-03-10 15:16:33.0",
            "2015-03-11 15:16:33.0",
            "2015-03-12 15:16:33.0",
            "2015-03-14 15:16:33.0",
            "2015-03-15 15:16:33.0",
            "2015-03-17 15:16:33.0",
            "2015-03-19 15:16:33.0"};
        List<Object> rows = new ArrayList<>();
        for (int ii=0; ii < revenues.length ; ii++) {
            Object[] row = new Object[]{revenues[ii], dates[ii]};
            rows.add(row);
        }

        return rows;
        
    }
}
