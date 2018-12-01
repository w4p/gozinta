package com.gz;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelCalculation {

    private BigInteger limit;
    private BigInteger current = BigInteger.ZERO;
    private BigInteger sum = BigInteger.ZERO;


    public ParallelCalculation(BigInteger limit) {
        this.limit = limit;
    }

    public synchronized BigInteger next() {
        this.current = current.add(BigInteger.ONE);
        if (this.current.compareTo(this.limit) < 0) {
            return this.current;
        }
        return null;
    }

    public synchronized void addToSum(BigInteger add) {
        this.sum = this.sum.add(add);
    }

    public BigInteger calculateSum() {
        int cores = Runtime.getRuntime().availableProcessors();
        CountDownLatch latch = new CountDownLatch(cores);
        ExecutorService executor = Executors.newFixedThreadPool(cores);
        IntStream.rangeClosed(1, cores).forEach((n) -> {
            executor.submit(() -> {
                System.out.println("Start thread " + n);
                BigInteger next;
                while ((next = this.next()) != null) {
                    if (next.divideAndRemainder(BigInteger.TEN.pow(3))[1].compareTo(BigInteger.ZERO) == 0) {
                        System.out.println("Calculating " + next);
                    }
                    BigInteger countChain = Gozinta.gnn(next);
                    if (countChain.compareTo(next) == 0) {
                        this.addToSum(countChain);
                    }
                }
                latch.countDown();
            });
        });

        try {
            latch.await();
            return this.sum;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
