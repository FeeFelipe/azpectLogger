//package io.azpect.logger.servlet;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.util.UUID;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ServletFilter implements Filter {
//	
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.print("ïnit");
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		String correlationtId = httpRequest.getHeader("correlation-id");
//		String requestId = UUID.randomUUID().toString();
//
//		if (correlationtId == null) {
//			correlationtId = requestId;
//		}
//
//		MDC.put("correlation-id", correlationtId);
//		MDC.put("request-id", requestId);
//		MDC.put("hostname", InetAddress.getLocalHost().getHostName());
//		MDC.put("ip", InetAddress.getLocalHost().getHostAddress());
//
//		httpResponse.setHeader("correlation-id", MDC.get("correlation-id"));
//		httpResponse.setHeader("request-id", MDC.get("request-id"));
//
//		try {
//			chain.doFilter(request, response);
//		} finally {
//			MDC.clear();
//		}
//	}
//
//	@Override
//	public void destroy() {
//		System.out.print("destroy");
//	}
//
//}
