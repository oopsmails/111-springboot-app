package com.oopsmails.springboot.mockbackend.util;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ResourceLoaderFile { //implements ApplicationContextAware {

//    private ApplicationContext applicationContext;
//
//    public Resource loadResourceByPath(String filePath) throws IOException {
////        Resource resource = applicationContext.getResource("file:c:\\testing.txt");
//        Resource resource = applicationContext.getResource(filePath);
//        return resource;
//    }
}
