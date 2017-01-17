package com.byrneham.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitialServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buf = new StringBuilder();
        buf.append("<html>");
        buf.append("<body>");
        buf.append("** Request from host: ").append(req.getHeader("host")).append("<br/>");
        buf.append("** Request context path: ").append(req.getContextPath()).append("<br/>");
        buf.append("</body>");
        buf.append("</html>");
        resp.getWriter().print(buf.toString());
    }

}
