package com.liwei.generate;

import java.io.File;

/**
 * @Author: li.wei
 * @Date: 2019/9/14 17:28
 * @Version
 */
public class Tes {
    public static void main(String[] args) {
        File file = new File("D:/java_myWorkSpace/target/classes/template");
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println(f.getName());
                }
            }
        }
    }
}
