package com.studydemo.demo.fromZeroPlusToOneHundred;

public class useSychronizedClass {

    public static void main(String[] args) throws InterruptedException {
        shareCounter shareCounter = new shareCounter(0);

        Thread thread1 = new Thread(new addDemo(shareCounter,50));
        Thread thread2 = new Thread(new addDemo(shareCounter,50));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(shareCounter.getValue());

    }


}

class shareCounter{

    private Integer value ;

    public synchronized void incrementValue(){
        value++;
    }

    public shareCounter(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

class addDemo implements Runnable{

    private shareCounter shareCounter;

    private Integer loopNum;

    public addDemo(com.studydemo.demo.fromZeroPlusToOneHundred.shareCounter shareCounter, Integer loopNum) {
        this.shareCounter = shareCounter;
        this.loopNum = loopNum;
    }

    @Override
    public void run() {
        for (int i = 0; i <loopNum ; i++) {
            shareCounter.incrementValue();
        }
    }
}