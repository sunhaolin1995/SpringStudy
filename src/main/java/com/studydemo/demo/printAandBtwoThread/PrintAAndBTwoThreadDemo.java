package com.studydemo.demo.printAandBtwoThread;

/**
 * @author 孙浩林
 * @date: 7/5/23 22:23
 */
public class PrintAAndBTwoThreadDemo {


    public static void main(String[] args) {
        PrintObject printObject = new PrintObject(1,50);

        new Thread(()->{
            printObject.print("A",1,2);
        }).start();
        new Thread(()->{
            printObject.print("B",2,1);
        }).start();
    }

}

class PrintObject {

    private final Integer loopNum;
    private Integer flag;
    private int loopCount;


    public PrintObject(Integer flag, Integer loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
        this.loopCount = 0;
    }

    public void print(String str, Integer currFlag, Integer nextFlag) {

        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                if (flag != currFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(str);
                loopCount++;
                flag = nextFlag;
                this.notifyAll();
            }
        }


    }


}
