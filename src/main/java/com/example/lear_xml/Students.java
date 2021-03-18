package com.example.lear_xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;

public class Students implements Serializable {
    public String name;
    private int age;
    private String sex;

    public Students() {
    }
    public Students(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public static void test(){
        System.out.println("好帅");
    }
    public void test2(){
        System.out.println("非静态方法");
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
