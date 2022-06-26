package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {
	private String viewPath;
	private boolean redirect;
	
	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}
	
	public MyView(String viewPath, boolean redirect) {
		this.viewPath = viewPath;
		this.redirect = redirect;
	}
	
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (redirect) {
			response.sendRedirect(viewPath);
		} else {			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
			dispatcher.forward(request, response);
		}
	}
}
