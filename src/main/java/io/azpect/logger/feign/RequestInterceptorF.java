//package io.azpect.logger.feign;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//
//@Component
//public class RequestInterceptorF implements RequestInterceptor {
//    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
// 
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (requestAttributes == null) {
//            return;
//        }
//        HttpServletRequest request = requestAttributes.getRequest();
//        if (request == null) {
//            return;
//        }
//        String language = request.getHeader(ACCEPT_LANGUAGE_HEADER);
//        if (language == null) {
//            return;
//        }
//        requestTemplate.header(ACCEPT_LANGUAGE_HEADER, language);
//    }
//}