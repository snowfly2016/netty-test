package com.test.jvmbyte;

/**
 * 现代JVM在执行Java代码的时候，通常都会将解释执行与编译执行二者结合起来进行；
 * 所谓解释执行，就是通过解释器来读取字节码，遇到响应的指令就去执行该指令；
 * 所谓编译执行，就是通过即时编译器Just In Time JIT将字节码转换为本地机器码来执行；现代JVM会根据代码热点来生成相应的本地机器码；
 *
 * 栈的指令集 JVM采用
 * 寄存器的指令集
 *
 * 基于栈的指令集与基于寄存器的指令集之间的关系：
 * 1.JVM执行指令时所采取的方式是基于栈的指令集
 * 2.基于栈的指令集主要的操作有入栈和出栈两种
 * 3.基于栈的指令集的优势在于它可以在不同的平台之间移植，而基于寄存器的指令集是与硬件架构紧密关联的，无法做到可移植；
 * 4.基于栈的指令集的缺点在于完成相同的操作，指令数量通常要比基于寄存器的指令集数量要多；基于栈的指令集是在内存中完成操作的，
 * 而基于寄存器的指令集是直接有cpu来执行的，它是在高速缓冲区中进行执行的，速度要快很多。
 * 虽然虚拟机可以采用一些优化手段，但总体来说，基于栈的指令集的执行速度要慢一些；
 *
 *
 */

/**
 * Classfile /Users/elm/Documents/project/netty-test/out/production/classes/com/test/jvmbyte/JavaTest5.class
 *   Last modified 2019-3-24; size 485 bytes
 *   MD5 checksum 22dad89c5afb60edce3d8d5dc7342cd1
 *   Compiled from "JavaTest5.java"
 * public class com.test.jvmbyte.JavaTest5
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #3.#21         // java/lang/Object."<init>":()V
 *    #2 = Class              #22            // com/test/jvmbyte/JavaTest5
 *    #3 = Class              #23            // java/lang/Object
 *    #4 = Utf8               <init>
 *    #5 = Utf8               ()V
 *    #6 = Utf8               Code
 *    #7 = Utf8               LineNumberTable
 *    #8 = Utf8               LocalVariableTable
 *    #9 = Utf8               this
 *   #10 = Utf8               Lcom/test/jvmbyte/JavaTest5;
 *   #11 = Utf8               testCalculate
 *   #12 = Utf8               ()I
 *   #13 = Utf8               a
 *   #14 = Utf8               I
 *   #15 = Utf8               b
 *   #16 = Utf8               c
 *   #17 = Utf8               d
 *   #18 = Utf8               result
 *   #19 = Utf8               SourceFile
 *   #20 = Utf8               JavaTest5.java
 *   #21 = NameAndType        #4:#5          // "<init>":()V
 *   #22 = Utf8               com/test/jvmbyte/JavaTest5
 *   #23 = Utf8               java/lang/Object
 * {
 *   public com.test.jvmbyte.JavaTest5();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 21: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/test/jvmbyte/JavaTest5;
 *
 *   public int testCalculate();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=6, args_size=1
 *          0: iconst_1
 *          1: istore_1
 *          2: iconst_2
 *          3: istore_2
 *          4: iconst_3
 *          5: istore_3
 *          6: iconst_4
 *          7: istore        4
 *          9: iload_1
 *         10: iload_2
 *         11: iadd
 *         12: iload_3
 *         13: isub
 *         14: iload         4
 *         16: imul
 *         17: istore        5
 *         19: iload         5
 *         21: ireturn
 *       LineNumberTable:
 *         line 24: 0
 *         line 25: 2
 *         line 26: 4
 *         line 27: 6
 *         line 28: 9
 *         line 29: 19
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      22     0  this   Lcom/test/jvmbyte/JavaTest5;
 *             2      20     1     a   I
 *             4      18     2     b   I
 *             6      16     3     c   I
 *             9      13     4     d   I
 *            19       3     5 result   I
 * }
 * SourceFile: "JavaTest5.java"
 *
 */
public class JavaTest5 {

    public int testCalculate(){
        int a=1;
        int b=2;
        int c=3;
        int d=4;
        int result = (a+b-c)*d;
        return result;
    }

}
