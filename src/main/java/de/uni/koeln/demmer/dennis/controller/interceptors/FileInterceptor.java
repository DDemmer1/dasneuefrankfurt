package de.uni.koeln.demmer.dennis.controller.interceptors;

import org.apache.commons.io.FileUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * Custom Interceptor um temporäre Dateien zu löschen
 *
 */
public class FileInterceptor implements HandlerInterceptor {

    /**
     * Entfernt alle temporären Dateien nach dem Request
     *
     * @param request
     * @param response
     * @param object
     * @param exeption
     * @throws Exception
     */
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


    /**
     * Entfernt alle temporären Dateien vor einem Request
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        FileUtils.cleanDirectory(new File("data/tmp"));
        FileUtils.cleanDirectory(new File("data/ner"));
        FileUtils.cleanDirectory(new File("data/in"));
        FileUtils.cleanDirectory(new File("data/out"));

        return true;
    }
}
