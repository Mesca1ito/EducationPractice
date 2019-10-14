package ru.dgaribov.hackerrank.javapracrice;

import java.math.BigDecimal;
import java.util.Arrays;

public class BigDecimalTask {
    public static void main(String[] args) {
        //Input
//        Scanner sc= new Scanner(System.in);
//        int n=sc.nextInt();
        String[] s = { "-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000"};
        int n = s.length;
//        for(int i=0;i<n;i++){
//            s[i]=sc.next();
//        }
//        sc.close();

        //Write your code here


        Arrays.sort(s, (o1, o2) -> {
            BigDecimal b1 = new BigDecimal(o1);
            BigDecimal b2 = new BigDecimal(o2);
            return b2.compareTo(b1);
        });

        //Output
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }

    }
}
