package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String directory = inputRootDir();
        if(directory == null){
            System.out.println("\nНеверная корневая папка");
            return;
        }
        FileManager fileManager = new FileManager(directory);
        var cycle = fileManager.relationsAreCorrect();
        if(cycle!=null){
            System.out.println("В следующих файлах найдены циклические зависимости:\n");
            for (String fileName: cycle){
                System.out.println(fileName+"\n");
            }
            return;
        }
        if(!fileManager.filesAreCorrect()){
            System.out.println("Некоторые файлы невозможно обработать");
            return;
        }
        fileManager.sort();
        fileManager.print();

    }


    //добавить проверку на корректность
    public static String inputRootDir(){
        System.out.println("Введите полный адрес корневой папки: ");
        Scanner in = new Scanner(System.in);
        String dirPath = in.nextLine();
        File directory = new File(dirPath);
        if(directory.exists() && directory.isDirectory()){
            return dirPath;
        }
        return null;
    }

}