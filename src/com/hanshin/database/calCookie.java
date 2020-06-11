package com.hanshin.database;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my3")
public class calCookie extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v_ = req.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = req.getParameter("operator");	

		Cookie[] cookies = req.getCookies();
		
		int result = 0;
		if(op.equals("=")) {
			// ����Ǿ� �ִ� ���� ���� �ҷ��� ���� �����ڿ� ���� ����Ͽ� result�� ����
			int prev = 0;
			String prev_op = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					prev = Integer.parseInt(c.getValue());
				}
				if(c.getName().equals("operator")){
					prev_op = c.getValue();
				}
			}
			
			if(prev_op.equals("+")) {
				result = prev + v;
			} else if(prev_op.equals("-")) {
				result = prev - v;
			}
			
			// ��� ���
			resp.getWriter().printf("��� = %d\n", result);
			
		} else {
			// ���� �����ڸ� ����
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie operatorCookie = new Cookie("operator", op);
			valueCookie.setMaxAge(60*60*2);
			
			resp.addCookie(valueCookie);
			resp.addCookie(operatorCookie);
			
			resp.sendRedirect("Calculator2.html");
			
		}
		
	}	
}
