package io.kubeless;

import com.alibaba.fastjson.JSONObject;
import io.kubeless.Event;
import io.kubeless.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://config.mokulive.stream:3306/storage?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String username = "root";
    private String password = "19831226lc";

    private Connection conn = null;

    public String getData(io.kubeless.Event event, io.kubeless.Context context) {
        System.out.println(event.Data);
        List result = new ArrayList();
        if("qryAllTypes".equals(event.Data)){
            result = qryAllTypes();
        }
        return JSONObject.toJSON(result).toString();
    }


    private void getConn() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map> qryAllTypes() {
        String sql = "select * from storage_type";
        conn = getConn();
        Statement stmt = null;
        ResultSet ret = null;
        List result = new ArrayList();
        String password = null;
        try {
            stmt = conn.createStatement();
            ret = stmt.executeQuery(sql);
            while (ret.next()) {
                Map map = new HashMap();
                map.put("typeid",ret.getString("typeid"));
                map.put("typename",ret.getString("typename"));
                map.put("parenttypeid",ret.getString("parenttypeid"));
                map.put("typecomment",ret.getString("typecomment"));
                result.add(map);
            }
            ret.close();
            stmt.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
