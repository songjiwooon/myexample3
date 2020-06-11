package com.hanshin.database;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/my4")
public class calHttpSession extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v_ = req.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = req.getParameter("operator");	
		
		HttpSession HttpSession = req.getSession();
		
		int result = 0;
		
		if(op.equals("=")) {
			// 저장되어 있던 값과 새로 불러온 값을 연산자에 따라 계산하여 result에 저장
			int prev = (int) HttpSession.getAttribute("value");
			String prev_op = (String) HttpSession.getAttribute("operator");
			
			if(prev_op.equals("+")) {
				result = prev + v;
			} else if(prev_op.equals("-")) {
				result = prev - v;
			}
			
			// 결과 출력
			resp.getWriter().printf("결과 = %d\n", result);
			
		} else {
			// 값이 들어오면 값을 저장하고 연산자가 들어오면 연산자를 저장
			HttpSession.setAttribute("value", v);
			HttpSession.setAttribute("operator", op);
			
			// 값 저장 후 계산기로 돌어감
			resp.sendRedirect("Calculator2.html");
			
		}
		
	}	
}
