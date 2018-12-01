package com.gz;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Gozinta {

    //NOT USING, DEV PURPOSE
    public static List<List<Integer>> gn(int n) {
        if (n > 1) {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 2; i < n; i++) {
                if (n % i < 1) {
                    for (List<Integer> var : gn(i)) {
                        var.add(n);
                        result.add(var);
                    }
                }
            }
            result.add(new ArrayList<>(Arrays.asList(1, n)));
            return result;
        } else {
            return Collections.singletonList(Collections.singletonList(1));
        }
    }

    //NOT USING, DEV PURPOSE
    public static Long gnn(int n) {
        if (n > 1) {
            AtomicLong am = new AtomicLong(0);
            for (int i = 2; i < n; i++) {
                if (n % i < 1) {
                    Long var = gnn(i);
                    am.addAndGet(var);
                }
            }
            return am.incrementAndGet();
        } else {
            return 1L;
        }
    }

    public static BigInteger gnn(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) > 0) {
            BigInteger am = BigInteger.valueOf(0l);
            for (BigInteger i = BigInteger.valueOf(2L); i.compareTo(n)<0; i=i.add(BigInteger.ONE)) {
                if (n.divideAndRemainder(i)[1].compareTo(BigInteger.ONE) < 0) {
                    BigInteger var = gnn(i);
                    am = am.add(var);
                }
            }
            am = am.add(BigInteger.ONE);
            return am;
        } else {
            return BigInteger.ONE;
        }
    }

    public static void main(String[] args) {

        ParallelCalculation pc = new ParallelCalculation(BigInteger.TEN.pow(16));
        System.out.println("TOTAL SUM" + pc.calculateSum());

    }
}
