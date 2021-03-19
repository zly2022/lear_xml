package com.servlet;

import com.bean.Phone;
import com.dao.PhoneDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/update")
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String flg = req.getParameter("flg");
        if(flg==null){
            //从修改进入的
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String ms = req.getParameter("ms");
            Phone phone = new Phone(Integer.parseInt(id),name,Double.parseDouble(price),ms);
            new PhoneDao().update(phone,"id");
            req.getRequestDispatcher("show").forward(req,resp);
        }else {
            //从显示页面进入
            Phone phone = new PhoneDao().get(new Phone(Integer.parseInt(id)),"id");
            req.setAttribute("phone",phone);
            req.getRequestDispatcher("update.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
