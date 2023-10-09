package com.studydemo.demo.ProducerAndConsumerDemo.awaitAndSignal;


public class Main {
    public static void main(String[] args) {
        StorageAwait storageAwait = new StorageAwait();
        Thread p1 = new Thread(new ProducerAwait(storageAwait));
        Thread p2 = new Thread(new ProducerAwait(storageAwait));
        Thread p3 = new Thread(new ProducerAwait(storageAwait));

        Thread c1 = new Thread(new ConsumerAwait(storageAwait));
        Thread c2 = new Thread(new ConsumerAwait(storageAwait));
        Thread c3 = new Thread(new ConsumerAwait(storageAwait));

        p1.start();
        p2.start();
        p3.start();

        c1.start();
        c2.start();
        c3.start();
    }

}
