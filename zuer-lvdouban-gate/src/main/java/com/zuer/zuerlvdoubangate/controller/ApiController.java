package com.zuer.zuerlvdoubangate.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@EnableAutoConfiguration
@RequestMapping(value = "/apicontroller")
@RestController
public class ApiController {

    @RequestMapping(value = "/addMovie",method = RequestMethod.POST)
    public void addMovie(@RequestParam Map<String, Object> param) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        try {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("调用zuer-lvdouban-auth服务");
                    countDownLatch.countDown();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("调用zuer-lvdouban-movie服务");
                    countDownLatch.countDown();
                }
            }).start();

            countDownLatch.await();
            System.out.println("新增电影成功");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("新增看过电影失败");
        }

    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        try {


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("调用zuer-lvdouban-auth服务");
                    countDownLatch.countDown();

                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("调用zuer-lvdouban-movie服务");
                    countDownLatch.countDown();
                }
            }).start();

            countDownLatch.await();
            System.out.println("新增电影成功");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("新增看过电影失败");
        }
    }
}
