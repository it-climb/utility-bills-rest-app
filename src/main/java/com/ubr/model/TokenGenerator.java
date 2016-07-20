package com.ubr.model;

import java.util.Date;

public class TokenGenerator {
    private static Date date = new Date();

    public static char[] generateRandomCharArray(int length) {
        char[] values = {'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z','0','1','2','3',
                '4','5','6','7','8','9','A','B','C','D',
                'E','F','G','H','I','J','K','L','M','N',
                'O','P','Q','R','S','t','U','V','W','X','Y','Z'};

        String out = "";
        long n = date.getTime()/(1000*60*60)/24;//
        for (int i=1;i<values.length;i++) {
            if (i%2==0){
                out+=values[(int)n%i];
            }else{
                out+=values[values.length-(int)n%i-1];
            }
        }
        if (out.length()>length){
            int begin = (out.length()-length)/2;
            int end =(out.length()-length)/2+length-1;
            out = out.substring(begin,end);
        }else{
            int number=length/out.length();
            for (int i=0;i<number+1;i++){
                out+=out;
            }
            int begin = (out.length()-length)/2;
            int end =(out.length()-length)/2+length-1;
            out = out.substring(begin,end);
        }
        return out.toCharArray();
    }
}
