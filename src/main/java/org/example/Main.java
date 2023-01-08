package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager=new FileManager(inputRootDir());
    }


    //добавить проверку на корректность
    public static String inputRootDir(){
        System.out.println("Введите полный адрес корневой папки: ");
        Scanner in = new Scanner(System.in);
        String rootDir = in.nextLine();
        return rootDir;
    }
}