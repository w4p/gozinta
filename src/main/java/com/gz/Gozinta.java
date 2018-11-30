package com.gz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gozinta {

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

    public static void main(String[] args) {
        List<List<Integer>> r = Gozinta.gn(48);
        System.out.println(r.size());
        System.out.println(r);
    }
}
