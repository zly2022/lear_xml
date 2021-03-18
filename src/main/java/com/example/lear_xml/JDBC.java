package com.example.lear_xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;

public class JDBC {
    private static String driver=null;
    private static String url=null;
    private static String user=null;
    private static String password=null;
    private static Connection con = null;

    private static Connection getCon() throws Exception {
        SAXReader saxReader = new SAXReader();
        File file = new File("src/main/webapp/WEB-INF/config.xml");
        Document doc = saxReader.read(file);
        Element element = doc.getRootElement();
        Iterator<Element> it = element.elementIterator("connection");
        while(it.hasNext()) {
            Element s = it.next();
            driver = s.elementText("driver");
            url = s.elementText("url");
            user = s.elementText("user");
            password= s.elementText("password");
        }
        System.out.println(driver);
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public int update(String sql,Object...args) throws Exception {
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1,args[i]);
        }
        return ps.executeUpdate();
    }
}
