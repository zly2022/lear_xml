package com.example.lear_xml;

import com.bean.Tuser;
import com.dao.TuserDao;
import com.util.DBUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws Exception {
		
		//JDBC j = new JDBC();
		//j.update("insert into Tuser values(?,?)","zz","zz");
		Tuser t = new TuserDao().get(new Tuser("zz"),"name");
		System.out.println(t.toString());

	}
	
}
