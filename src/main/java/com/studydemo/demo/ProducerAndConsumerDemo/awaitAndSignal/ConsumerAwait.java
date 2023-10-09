package com.studydemo.demo.ProducerAndConsumerDemo.awaitAndSignal;



public class ConsumerAwait implements Runnable{

    private StorageAwait storageAwait;

    public ConsumerAwait(StorageAwait storageAwait) {
        this.storageAwait = storageAwait;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            storageAwait.consumer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
