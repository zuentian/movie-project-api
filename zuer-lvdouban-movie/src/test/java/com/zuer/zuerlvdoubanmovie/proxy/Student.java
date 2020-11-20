package com.zuer.zuerlvdoubanmovie.proxy;

public class Student implements Person {
    @Override
    public void giveMoney() {
        try {
            //假设数钱花了1秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Student giveMoney()" + "上交班费50元");
    }

    @Override
    public void giveFamilyWork() {
        try {
            //假设做作业花了3秒时间
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Student giveFamilyWork()" + "交数学作业");
    }
}
