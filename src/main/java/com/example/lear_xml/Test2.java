package com.example.lear_xml;

public class Test2 {
    public static void ts(int... a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
    public static void main(String[] args) {
        ts(21,32,12);
    }
}
