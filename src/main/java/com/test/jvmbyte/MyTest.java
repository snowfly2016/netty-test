package com.test.jvmbyte;

/**
 * *************************************************************************
 * javap com.test.jvmbyte.MyTest
 * Compiled from "MyTest.java"
 * public class com.test.jvmbyte.MyTest {
 *   public com.test.jvmbyte.MyTest();
 *   public int getA();
 *   public void setA(int);
 * }
 **************************************************************************
 * javap -c com.test.jvmbyte.MyTest
 * Compiled from "MyTest.java"
 * public class com.test.jvmbyte.MyTest {
 *   public com.test.jvmbyte.MyTest();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: aload_0
 *        5: iconst_1
 *        6: putfield      #2                  // Field a:I
 *        9: return
 *
 *   public int getA();
 *     Code:
 *        0: aload_0
 *        1: getfield      #2                  // Field a:I
 *        4: ireturn
 *
 *   public void setA(int);
 *     Code:
 *        0: aload_0
 *        1: iload_1
 *        2: putfield      #2                  // Field a:I
 *        5: return
 * }
 *************************************************************************
 * javap -verbose com.test.jvmbyte.MyTest
 * Classfile /Users/elm/Documents/project/netty-test/out/production/classes/com/test/jvmbyte/MyTest.class
 *   Last modified 2019-3-14; size 476 bytes
 *   MD5 checksum 2480212fc942254ea8c83519945883a7
 *   Compiled from "MyTest.java"
 * public class com.test.jvmbyte.MyTest
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #4.#20         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #3.#21         // com/test/jvmbyte/MyTest.a:I
 *    #3 = Class              #22            // com/test/jvmbyte/MyTest
 *    #4 = Class              #23            // java/lang/Object
 *    #5 = Utf8               a
 *    #6 = Utf8               I
 *    #7 = Utf8               <init>
 *    #8 = Utf8               ()V
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               LocalVariableTable
 *   #12 = Utf8               this
 *   #13 = Utf8               Lcom/test/jvmbyte/MyTest;
 *   #14 = Utf8               getA
 *   #15 = Utf8               ()I
 *   #16 = Utf8               setA
 *   #17 = Utf8               (I)V
 *   #18 = Utf8               SourceFile
 *   #19 = Utf8               MyTest.java
 *   #20 = NameAndType        #7:#8          // "<init>":()V
 *   #21 = NameAndType        #5:#6          // a:I
 *   #22 = Utf8               com/test/jvmbyte/MyTest
 *   #23 = Utf8               java/lang/Object
 * {
 *   public com.test.jvmbyte.MyTest();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: aload_0
 *          5: iconst_1
 *          6: putfield      #2                  // Field a:I
 *          9: return
 *       LineNumberTable:
 *         line 3: 0
 *         line 5: 4
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      10     0  this   Lcom/test/jvmbyte/MyTest;
 *
 *   public int getA();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: getfield      #2                  // Field a:I
 *          4: ireturn
 *       LineNumberTable:
 *         line 8: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/test/jvmbyte/MyTest;
 *
 *   public void setA(int);
 *     descriptor: (I)V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: aload_0
 *          1: iload_1
 *          2: putfield      #2                  // Field a:I
 *          5: return
 *       LineNumberTable:
 *         line 12: 0
 *         line 13: 5
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       6     0  this   Lcom/test/jvmbyte/MyTest;
 *             0       6     1     a   I
 * }
 * SourceFile: "MyTest.java"
 **************************************************************************
 * hex fiend
 *
 * 1.使用Javap -verbose命令分析一个字节码文件时，将会分析该字节码文件的魔数、版本号、常量池、类信息、类的构造方法、类中方法信息、类变量与成员变量等信息；
 *
 * 2.魔数：所有的.class字节码文件的前4个字节信息都是魔数，魔数值为固定值：0xCAFEBABE
 *
 * 3.魔数之后的4个字节为版本信息，前两个字节表示minor version（次版本号），后两个字节表示major version（主版本号）。这里的版本号为00 00 00 34，换算成十进制，表示次版本号为0，主版本号为52，所以，该文件的版本号为：1.8.0。可以通过java -version命令来验证这一点
 *
 * 4.常量池constant pool：紧接着主版本号之后的就是常量池入口。一个Java类中定义的很多信息都是由常量池来维护和描述的，可以将常量池看作是class文件的资源仓库，比如说Java类中定义方法与变量信息，都是存储在常量池中。常量池中主要存储两类常量：字面量与符号引用。字面量如文本字符串，Java中声明为final的常量值等，而符号引用如类和接口的全限定名，字段的名称和描述符，方法的名称和描述符等。
 *
 * 5.常量池的总体结构：Java类所对应的常量池主要由常量池数量与常量池数组（常量表）这两部分组成。常量池数量紧跟在主版本号后面，占据2个字节；常量池数组则紧跟在常量池数量之后。常量池数组与一般的数组不同的是，常量池数组中不同的元素的类型、结构都是不同的，长度当然也就是不同；但是，每一种元素的第一个数据都是一个u1类型，该字节是个标志位，占据1个字节。JVM在解析常量池时，会根据这个u1类型来获取元素的具体类型。值得注意的是，常量池数组中元素的个数=常量池-1（其中0暂时不使用），目的是满足某些常量池索引值的数据在特定情况下需要表达【不引用任何一个常量池】的含义；根本原因在于，索引为0也是一个常量（保留常量），只不过它不位于常量表中，这个常量就对应null值；所以，常量池的索引从1而非0开始。
 *
 * 6.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class MyTest {

    private  int a = 1;

    public int getA(){
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }


}
