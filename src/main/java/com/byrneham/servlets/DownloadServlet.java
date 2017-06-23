package com.byrneham.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class DownloadServlet extends HttpServlet {

    static int bufSize = 1048576;
    byte[] bytes = new byte[bufSize];
    static int nCopies = 100;
    long time = new Date().getTime();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        String megStr = req.getParameter("m");
        try {
            nCopies = Integer.parseInt(megStr);
        } catch (NumberFormatException e) {
        }
        int imageLength = bufSize * nCopies;
        response.setContentType("audio/mpeg3"); //"image/gif");
        response.setContentLength(imageLength);
        response.addHeader("Accept-Ranges", "bytes");
        String tag = Long.toHexString(time);
        response.addHeader("ETag", tag);
        response.addHeader("Cache-Control", "max-age=604800");// One week
        response.setDateHeader("Last-Modified", time);
        OutputStream os = response.getOutputStream();
        for (int i = 0; i < nCopies; i++){
            os.write(bytes);
        }
        os.close();
    }
}
