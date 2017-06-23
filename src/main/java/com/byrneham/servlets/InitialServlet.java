package com.byrneham.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InitialServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        work(req.getHeader("host"),req.getContextPath(), resp.getWriter());
    }

    public static void work(String host, String contextPath, PrintWriter writer ){
        StringBuilder buf = new StringBuilder();
        buf.append("<html>");
        buf.append("<body>");
        buf.append("** Request from host: ").append(host).append("<br/>");
        buf.append("** Request context path: ").append(contextPath).append("<br/>");
        buf.append("</body>");
        buf.append("</html>");
        writer.print(buf.toString());
    }

}
