/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author howar
 */
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // code that is executed before the servlet
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
            if(user.getRole().getRoleId() ==2){
                 HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.sendRedirect("notes");
                return;
            }


        chain.doFilter(request, response); //execute the servlet

        //code that is executed after the servlet
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
