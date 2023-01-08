package org.example;

import java.io.*;
import java.util.ArrayList;


public class FileStruct {
    private final String fileName;
    private final ArrayList<String> text = new ArrayList<>();


    FileStruct(String fileName) {
        this.fileName = fileName;
        setText();
    }
    //equals and hashcode??



    public String getFileName() {
        return fileName;
    }
    public ArrayList<String> getText(){
        return text;
    }

    private void setText() {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                text.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
