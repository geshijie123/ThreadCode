package com.example.taskdemo.service;

import org.springframework.stereotype.Service;


/**
 * @author geshijie
 * @date 2019-06-20 16:26
 */
@Service
public class OneService {

    /** 模拟耗时2500ms */
    public String getInfo(String s) {
        try {
           Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "info : "+s;
    }

    /** 模拟耗时3500ms */
    public String getDesc(String s) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "desc : "+ s.hashCode();
    }
    /** 模拟耗时3500ms */
    public void print(String s) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()+"log ->  "+s);
    }
}
