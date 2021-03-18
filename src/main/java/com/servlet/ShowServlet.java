package com.servlet;

import com.bean.Phone;
import com.dao.PhoneDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name= (String) req.getAttribute("name");
        String password= (String) req.getAttribute("password");
        List<Phone> phone = new PhoneDao().list(new Phone().getClass());
        req.setAttribute("phone",phone);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
