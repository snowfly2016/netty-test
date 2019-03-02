package com.test.jvm;

/**
 * MyParent4的静态代码块会输出吗？
 * 会，因为是首次主动使用
 *
 * 对于数组实例来说，其类型是有JVM在运行期动态生成的，表示为[Lcom.test.jvm.MyParent4这种形式,动态生成的类型，其父类型就是Object；
 * 对于数据来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型；
 *
 * 助记符：
 * anewarray:表示创建一个引用类型的（如类、接口、数组）数组，并将其引用值压人栈顶
 * newarray:表示创建一个指定的原始类型（如果int float char等）的数组，并将其引用值压人栈顶
 */
public class JvmTest3 {

    public static void main(String[] args) {
        //MyParent4 myParent4 = new MyParent4();
        //System.out.println("-----------------------------");
        //MyParent4 myParent5 = new MyParent4();

        //数组的时候，不会输出静态代码块
        MyParent4[] myParents = new MyParent4[1];
        //class [Lcom.test.jvm.MyParent4; 虚拟机在运行期帮我们创建的
        System.out.println(myParents.getClass());
        System.out.println(myParents.getClass().getSuperclass());
        System.out.println("--------------------1-----------------------");
        //二维数组
        MyParent4[][] myParentss = new MyParent4[1][1];
        //class [[Lcom.test.jvm.MyParent4;
        System.out.println(myParentss.getClass());
        System.out.println(myParentss.getClass().getSuperclass());
        System.out.println("--------------------2-----------------------");
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
        System.out.println("--------------------3-----------------------");
        char[] chars = new char[1];
        System.out.println(chars.getClass());
        System.out.println("--------------------4-----------------------");
        boolean[] booleans = new boolean[1];
        System.out.println(booleans.getClass());
        System.out.println("--------------------5-----------------------");
        short[] shorts= new short[1];
        System.out.println(shorts.getClass());
        System.out.println("--------------------6-----------------------");
        byte[] bytes= new byte[1];
        System.out.println(bytes.getClass());
    }
}

class MyParent4{
    static {
        System.out.println("MyParent4 static code");
    }
}