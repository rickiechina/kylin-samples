package com.rickie.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
public class KylinController {
    @GetMapping("/sales")
    public List<String> queryFromKylin() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
        Properties info = new Properties();
        info.put("user", "ADMIN");
        info.put("password", "KYLIN");
        Connection conn = driver.connect("jdbc:kylin://centos-103:7070/learn_kylin", info);
        Statement state = conn.createStatement();
        ResultSet resultSet = state.executeQuery("select part_dt, sum(price) as sum_price " +
                "from kylin_sales " +
                "group by part_dt order by part_dt limit 10");
        List<String> strList = new ArrayList<>();
        while(resultSet.next()) {
            String col1 = resultSet.getString(1);
            String col2 = resultSet.getString(2);
            String str = col1 + " " + col2;
            System.out.println(str);
            strList.add(str);
        }
        conn.close();
        return strList;
    }

    @GetMapping("/sales/{seller_id}")
    public List<String> queryByPreparedStatement(@PathVariable("seller_id") long seller_id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
        Properties info = new Properties();
        info.put("user", "ADMIN");
        info.put("password", "KYLIN");
        Connection conn = driver.connect("jdbc:kylin://centos-103:7070/learn_kylin", info);
        PreparedStatement state = conn.prepareStatement("select part_dt, sum(price) as sum_price " +
                "from kylin_sales " +
                "where seller_id=?" +
                "group by part_dt order by part_dt limit 10");
        state.setLong(1, seller_id);
        ResultSet resultSet = state.executeQuery();
        List<String> strList = new ArrayList<>();
        while(resultSet.next()) {
            String col1 = resultSet.getString(1);
            String col2 = resultSet.getString(2);
            String str = col1 + " " + col2;
            System.out.println(str);
            strList.add(str);
        }
        conn.close();
        return strList;
    }
}
