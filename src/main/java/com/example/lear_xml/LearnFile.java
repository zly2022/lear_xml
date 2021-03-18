package com.example.lear_xml;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnFile {
    public static void main(String[] args) throws Exception {
//        Connection con = new JDBC().getCon();
//        Statement sta = con.createStatement();
//        String sql = "select *from emp";
//        ResultSet rs = sta.executeQuery(sql);//执行查询返回结果集
//        while(rs.next()){
//            int empno = rs.getInt("empno");
//            String ename = rs.getString("ename");
//            System.out.println(empno+" "+ename);
//        }
        JDBC j = new JDBC();
        j.update("insert into student values(?,?,?,?)","1001","李四",23,"男");

    }
}
