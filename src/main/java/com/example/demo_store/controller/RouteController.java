package com.example.demo_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public List<String> listAllRoutes() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        List<String> paths = new ArrayList<>();
        for (RequestMappingInfo info : handlerMethods.keySet()) {
            Set<String> patterns = info.getPatternValues(); // Lấy ra path
            paths.addAll(patterns);
        }

        return paths.stream().distinct().sorted().toList();
    }
}
