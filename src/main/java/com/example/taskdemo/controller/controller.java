package com.example.taskdemo.controller;

import com.example.taskdemo.service.OneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author geshijie
 * @date 2019-06-20 16:26
 */
@RestController
public class controller {


    @Autowired
    TaskExecutor taskExecutor;
    @Autowired
    OneService service;

    @RequestMapping("/do")
    public Object fun(String s){

        long str = System.currentTimeMillis();

        Callable<String> callable1=()->service.getInfo(s);
        Callable<String> callable2=()->service.getDesc(s);

        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        taskExecutor.execute(futureTask1);
        taskExecutor.execute(futureTask2);

        String rs= null;
        try {
            rs = futureTask1.get()+"  "+futureTask2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return rs+ "消耗时间 ："+(System.currentTimeMillis()-str)+"ms";
    }


    @RequestMapping("/old_do")
    public Object fun_old(String s){

        long str = System.currentTimeMillis();
        String info = service.getInfo(s);
        String desc = service.getDesc(s);
        String rs = info+"  "+desc;

        return rs+ "消耗时间 ："+(System.currentTimeMillis()-str)+"ms";
    }


    @RequestMapping("/out_do")
    public Object fun_out(String s){

        long str = System.currentTimeMillis();
        taskExecutor.execute(()->service.print(s));
        return "消耗时间 ："+(System.currentTimeMillis()-str)+"ms";
    }
    @RequestMapping("/out_do_old")
    public Object fun_out_old(String s){

        long str = System.currentTimeMillis();
        service.print(s);
        return "消耗时间 ："+(System.currentTimeMillis()-str)+"ms";
    }
}
