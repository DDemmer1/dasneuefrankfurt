package de.uni.koeln.demmer.dennis.controller.interceptors;

import org.apache.commons.io.FileUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class FileInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exeption) throws Exception {
        FileUtils.cleanDirectory(new File("data/tmp"));
        FileUtils.cleanDirectory(new File("data/ner"));
        FileUtils.cleanDirectory(new File("data/in"));
        FileUtils.cleanDirectory(new File("data/out"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        FileUtils.cleanDirectory(new File("data/tmp"));
        FileUtils.cleanDirectory(new File("data/ner"));
        FileUtils.cleanDirectory(new File("data/in"));
        FileUtils.cleanDirectory(new File("data/out"));

        return true;
    }
}
