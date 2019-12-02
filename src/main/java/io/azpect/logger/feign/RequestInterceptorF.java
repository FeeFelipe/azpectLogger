package io.azpect.logger.feign;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class RequestInterceptorF implements RequestInterceptor {
	
	@Value("${io.azpectLogger.correlation-id:#{null}}")
	private String correlationId;
	
	@Value("${io.azpectLogger.trace-id:#{null}}")
	private String traceId;
	
	@Value("${io.azpectLogger.parent-id:#{null}}")
	private String parentId;
 
    @Override
    public void apply(RequestTemplate requestTemplate) {
        
        
//        requestTemplate.headers();
        

		if (correlationId != null)
			requestTemplate.header(correlationId, MDC.get(correlationId));
		
		if (traceId != null)
			requestTemplate.header(traceId, MDC.get(traceId));
		
		if (parentId != null)
			requestTemplate.header(parentId, MDC.get(parentId));
    }
}