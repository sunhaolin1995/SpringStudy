package com.studydemo.demo.ProducerAndConsumerDemo.waitAndNotify;

public class Consumer implements Runnable{

    private Storage storage;

    public Consumer() {
    }

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            storage.consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
