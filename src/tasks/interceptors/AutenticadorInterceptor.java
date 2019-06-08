package tasks.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutenticadorInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();
		System.out.println("Mapeando interceptor...");
		
		if (uri.endsWith("formlogin") || uri.endsWith("getlogin") || uri.contains("resources")) {
			return true;
		}
		
		if (request.getSession().getAttribute("usuariologado") != null) {
			return true;
		}
		
		response.sendRedirect("loginform");
		return false;
	}
}
