package com.adam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by adam on 03/04/15.
 */
public class First extends HttpServlet
{

    private File htmlFile = new File("/home/adam/Desktop/TomcatExtracted/apache-tomcat-8.0.21/webapps/IntellijJeeApp/First.html");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        writeFromHtmlFileToResponse(resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String message = new String(req.getParameter("cos") + "</br>from: "+req.getRemoteAddr());
        writeFromHtmlFileToResponse(resp, message);

    }

    private void writeFromHtmlFileToResponse(HttpServletResponse resp, String postedString) throws IOException
    {
        PrintWriter pw = resp.getWriter();
        BufferedReader br = new BufferedReader(new FileReader(htmlFile));

        String s;
        do
        {
            s = br.readLine();
            if(s.equals("<!--<p></p>-->"))
                pw.println("<p>" + postedString + "</p>");
            if(s!=null)
                pw.println(s);
        }while (s!=null);

        br.close();
        pw.close();
    }

    private void writeFromHtmlFileToResponse(HttpServletResponse resp) throws IOException
    {
        PrintWriter pw = resp.getWriter();
        BufferedReader br = new BufferedReader(new FileReader(htmlFile));

        String s;
        do
        {
            s = br.readLine();
            if(s!=null)
                pw.println(s);
        }while (s!=null);

        br.close();
        pw.close();
    }

}
