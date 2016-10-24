package com.sgk.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class ExecutionTimeInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME_ATTR_NAME = "startTime";
    private static final String EXECUTION_TIME_ATTR_NAME = "executionTime";

    /**
     * This method is called before the actual handler is executed
     * This implementation always returns true.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTR_NAME, startTime);
        return true;
    }

    /**
     * This method is called after the actual handler is executed.
     *
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     * @param handler      Object
     * @param modelAndView ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute(START_TIME_ATTR_NAME);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if (modelAndView != null) {
            modelAndView.addObject(EXECUTION_TIME_ATTR_NAME, executionTime);
        }

        if (log.isDebugEnabled()) {
            log.debug("[" + handler + "] executeTime : " + executionTime + "ms");
        }
    }
}
