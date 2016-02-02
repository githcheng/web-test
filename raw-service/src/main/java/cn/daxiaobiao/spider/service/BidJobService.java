package cn.daxiaobiao.spider.service;

import cn.daxiaobiao.spider.service.impl.AddBidService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cheng on 2016/1/5.
 */
public class BidJobService {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:daxiaobiao-spider-service.xml");
        AddBidService addBidService = (AddBidService)context.getBean(AddBidService.class);
        addBidService.excute();
    }
}
