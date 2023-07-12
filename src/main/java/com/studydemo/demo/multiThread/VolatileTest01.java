package com.studydemo.demo.multiThread;

/**
 * @author 孙浩林
 * @date: 5/25/23 16:02
 */
public class VolatileTest01 {
    volatile int i;

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest01 test01 = new VolatileTest01();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test01.addI();
                }
            }).start();
        }
        Thread.sleep(10000);//等待10秒，保证上面程序执行完成
        System.out.println(test01.i);
    }

    public void addI() {
        i++;
    }


}
