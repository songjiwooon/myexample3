package com.hanshin.database;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my5")
public class calServletContext extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v_ = req.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = req.getParameter("operator");	
		
		ServletContext servletcontext = req.getServletContext();
		
		int result = 0;
		
		if(op.equals("=")) {
			// ����Ǿ� �ִ� ���� ���� �ҷ��� ���� �����ڿ� ���� ����Ͽ� result�� ����
			int prev = (int) servletcontext.getAttribute("value");
			String prev_op = (String) servletcontext.getAttribute("operator");
			
			if(prev_op.equals("+")) {
				result = prev + v;
			} else if(prev_op.equals("-")) {
				result = prev - v;
			}
			
			// ��� ���
			resp.getWriter().printf("��� = %d\n", result);
			
		} else {
			// ���� ������ ���� �����ϰ� �����ڰ� ������ �����ڸ� ����
			servletcontext.setAttribute("value", v);
			servletcontext.setAttribute("operator", op);
			
			// �� ���� �� ����� ���
			resp.sendRedirect("Calculator2.html");
		}
		
	}	
}