package com.codegym;

import com.sun.xml.internal.bind.v2.util.EditDistance;

import java.util.Random;

public class Car implements Runnable{
    private static final int STEP = 2;
    private static int DISTANCE = 100;
    private String name;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // khởi tạo điểm start (km ban đầu)
        int runDistance = 0;
        // Khởi tạo time bắt đầu cuộc đua
        long startTime = System.currentTimeMillis();
        // kiểm tra chừng nào còn xe chưa kết thúc thì xe vẫn chạy
        while (runDistance<DISTANCE ){
            try {
// random Speed km/h
                int speed = (new Random()).nextInt(20);
                // calculate traveled distance
                runDistance += speed;
                // build result graphic
                String log = "|";
                int percentTravel = (runDistance*100)/DISTANCE;
                for (int i = 0; i < DISTANCE; i+= STEP) {
                    if (percentTravel >= i + STEP){
                        log += "=";
                    }else if (percentTravel >= i && percentTravel < i +STEP){
                        log += "o";
                    }else {
                        log += "-";
                    }
                }
                log += "|";
                System.out.println("car "+this.name+ ": " +log+ " " + Math.min(DISTANCE,runDistance)+"km");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Car" + this.name + " broken...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("car "+this.name+" finish in"+(endTime - startTime)/1000+" s");
    }
}
