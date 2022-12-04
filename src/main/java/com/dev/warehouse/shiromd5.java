package com.dev.warehouse;

import org.apache.shiro.crypto.hash.Md5Hash;

public class shiromd5 {
    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }

    private static String md5(String source) {
        Md5Hash hash1 = new Md5Hash("123456");
        System.out.println("MD5: " + hash1);

        Md5Hash hash2 = new Md5Hash("123456", "3D5548DA5BAB4BBBB5B5C0BA4948B4C8");
        System.out.println("MD5 with salt: " + hash2.toString());

        Md5Hash hash3 = new Md5Hash("123456", "04A93C74C8294AA09A8B974FD1F4ECBB", 2);
        System.out.println("MD5 with salt after 2 iterations: " + hash3.toString());

        return source;
    }
}
