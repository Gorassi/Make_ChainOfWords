package com.javarush.task.task22.task2209;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {

        String inputString = "Киев Алма-Ата Нью-Йорк Москва Амстердам Вена Адис-Абеба Мельбурн";

        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            String fileName = bufferedReader.readLine();
//            bufferedReader = new BufferedReader(new InputStreamReader(
//                    new FileInputStream(new File( fileName))));
//            inputString = bufferedReader.readLine();
//            bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if(inputString == " " || inputString == "" || inputString == null ) inputString = "";

        String lowCase = inputString.toLowerCase();
        String[] massive = lowCase.split(" ");
        int lenght = massive.length;
        StringBuilder result = null;
        while(true) {
            result = getLine(massive);
            if (!result.toString().contains("$")) break;
        }
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words){
        int len = words.length;
        if(len == 0 || len == 1 && words[0].equals("")) return new StringBuilder();
        int randomNumber = (int) (Math.random()*len);

        String oldStart = words[0];
        words[0] = words[randomNumber];
        words[randomNumber]= oldStart;

        StringBuilder stringBuilder = getNewLine(words);

        return stringBuilder;
    }

    public static StringBuilder getNewLine(String... words) {
        String[] oldMassive = words;
        int len = words.length;
        if(len == 1) return
                new StringBuilder(words[0].substring(0,1).toUpperCase() + words[0].substring(1));
        if(len == 0) return
                new StringBuilder("_$_len==0_");

        String[] newMassive = new String[len - 1 ];

        String lastChar = words[0].substring(words[0].length() - 1);

        int index = -1;
        int count = 0;

        for(int i = 1; i < len; i++) {
            if(words[i].startsWith(lastChar))  {
                count++;
                index = i;
            }
        }
        if(count == 0) {
//            System.out.println(" count = 0 ");
            return new StringBuilder(" $ ");
        }
        if(count > 1){
//            System.out.println("count > 1 ");
            int[] positions = new int[count];
            int position = 0;
//            System.out.println("lastChar = " + lastChar);
            for(int i = 1; i < len; i++) {
                if(words[i].startsWith(lastChar))  {
                    positions[position]= i;
//                    System.out.println("words = " + words[i] + " i = "
//                    + i + " position = " + position);
                    position++;
                }
            }
        int random = (int) (Math.random()*count);
            index = positions[random];
//            System.out.println("index = " + index + " position = " + positions[random] +
//                    words[positions[random]]);
        }

        for(int i = 1; i < words.length; i++) {
            newMassive[i - 1] = oldMassive[i];
        }

        if(index > 1){
            String str = newMassive[0];
            newMassive[0]= newMassive[index - 1];
            newMassive[index - 1] = str;
        }

        if(index == -1) {
            Arrays.stream(words).forEach(System.out::print);
            System.out.println();
            return new StringBuilder(words[0]).append(" $ ");
        }

        String result = words[0].substring(0,1).toUpperCase() + words[0].substring(1)
                + " " + getNewLine(newMassive);
        return new StringBuilder(result);
    }

}
