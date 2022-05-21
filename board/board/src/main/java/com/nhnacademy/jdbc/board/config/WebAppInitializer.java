package com.nhnacademy.jdbc.board.config;

import com.nhnacademy.jdbc.board.compre.filter.XssEscapeFilter;
import com.nhnacademy.jdbc.board.compre.filter.XssEscapeServletFilter;
import com.nhnacademy.jdbc.board.compre.filter.XssEscapeServletFilterWrapper;
import com.nhnacademy.jdbc.board.compre.filter.defender.XssSaxFilterDefender;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{com.nhnacademy.jdbc.board.config.RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        XssEscapeServletFilter xssEscapeServletFilter = new XssEscapeServletFilter();

        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter,
            xssEscapeServletFilter};
    }
}
