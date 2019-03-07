package com.test.jvm;

import com.sun.crypto.provider.AESKeyGenerator;

public class JvmTest19 {

    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());

        System.out.println(JvmTest19.class.getClassLoader());
    }
}
