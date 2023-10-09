package com.studydemo.demo.ProducerAndConsumerDemo.awaitAndSignal;


public class ProducerAwait implements Runnable{

    private StorageAwait storageAwait;

    public ProducerAwait(StorageAwait storageAwait) {
        this.storageAwait = storageAwait;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            storageAwait.produce();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
