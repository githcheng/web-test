package cn.daxiaobiao.es.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cheng on 2016/2/17.
 */
public class ESJob {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:daxiaobiao-es-service.xml");
        EsJobService esService = (EsJobService)context.getBean(EsJobService.class);
        esService.updateEs();
    }
}
