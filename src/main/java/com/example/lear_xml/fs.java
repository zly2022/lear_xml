package com.example.lear_xml;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class fs {
    public static void main(String[] args) throws Exception {
        Students s = new Students();
        //1、通过机场Objiect父类的getClass()方法
        Class c1 = s.getClass();
        //2、通过类的静态class属性获取
        Class c2 = Students.class;
        //3、通过Class.forName()
        Class c3 = Class.forName("com.example.lear_xml.Students");
        //操作
        System.out.println(c1.getName()+":成员变量个数"+c3.getDeclaredFields().length);
        System.out.println(c1.getName()+":方法个数"+c3.getDeclaredMethods().length);
        System.out.println(c1.getName()+":构造方法个数"+c3.getConstructors().length);
        //等价于new操作
        Students students = (Students) c3.newInstance();
        students.setName("李四");
        students.setSex("男");
        students.setAge(23);
        System.out.println(students.toString());
        //属性操作
        //获取属性数组
        Field []fs = c3.getDeclaredFields();
        for (Field f:fs){
            //1、取值时如果是私有的，则会报错，暴力获取，取消访问修饰
            f.setAccessible(true);
            System.out.println("访问修饰符："+f.getModifiers()+"数据类型："+f.getType()+"字段名称："+f.getName()+"值："+f.get(students));

            //2、不建议暴力破解，通过get方法
            String name = "get"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1);
            //通过方法名称获取指定Method对象
            Method m = c3.getDeclaredMethod(name);
            //执行调用方法
            Object o = m.invoke(c3.newInstance());
            System.out.println(f.toGenericString()+"\t"+o );
        }
        //获取方法数组
        Method[] ms = c3.getDeclaredMethods();
        for(Method m : ms){
            System.out.println(m.toGenericString());
        }
        //通过反射调用其对象中的方法(静态方法)
        ms[3].invoke(null);
        //调用非静态方法
        ms[6].invoke(c3.newInstance());






    }
}
