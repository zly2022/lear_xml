package com.servlet;

import com.bean.Tuser;
import com.dao.TuserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Tuser tuser = new Tuser();
        List<Tuser> t = new TuserDao().list(new Tuser().getClass());
        for (Tuser s:t
             ) {
            if(s.getName().equals(name)&&s.getPassword().equals(password)){
                req.setAttribute("name",name);
                req.setAttribute("password",password);
                //创建Cookie对象
                Cookie c1 = new Cookie("username",name);
                Cookie c2 = new Cookie("password",password);
                //设置Cookie保存时间(单位:秒)
                c1.setMaxAge(60);
                c2.setMaxAge(60);
                //通过响应对象添加到客户端
                resp.addCookie(c1);
                resp.addCookie(c2);

                req.getRequestDispatcher("show").forward(req,resp);
            }
        }
        resp.sendRedirect("login.jsp?flg=0");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
