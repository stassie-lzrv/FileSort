package org.example;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Objects;

public class FileManager {
    private final FileGraph fileGraph = new FileGraph();
    private final String rootDirectory;

    FileManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        getFiles(rootDirectory);

    }

    /**
     * Чтение и добавление файлов и зависимостей в граф
     */
    private void getFiles(String directory) {
        File dir = new File(directory); //path указывает на директорию
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                fileGraph.addVertex(file.getAbsolutePath());
                setRelations(file);
            }
            else if(file.isDirectory()){
                getFiles(file.getAbsolutePath());
            }
        }
    }



    /**
     * Установка зависимостей
     * @param file название файла
     */
    private void setRelations(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("require")) {
                    String childPath = rootDirectory + FileSystems.getDefault().getSeparator() + line.substring(9, line.length() - 1);
                    fileGraph.addEdge(file.getAbsolutePath(), childPath);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Проверка на существование файла
     * @return файл существует
     */
    public boolean filesAreCorrect() {
        for (String filePath : fileGraph.adjList.keySet()) {
            File file = new File(filePath);
            if (!file.exists()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка на правильность зависимостей
     * @return зависимости корректны
     */
    public ArrayList<String> relationsAreCorrect() {
        return fileGraph.detectCycle();
    }

    /**
     * Сортировка графа
     */
    public void sort() {
        fileGraph.sortFiles();
    }

    /**
     * Конкатенация файлов
     */
    public void print() {
        for (String filePath : fileGraph.sorted) {
            System.out.println(filePath.substring(rootDirectory.length()+1));
        }
        StringBuilder text = new StringBuilder();
        for (String filePath : fileGraph.sorted) {
            try (FileReader reader = new FileReader(filePath)) {
                int symbol;
                while ((symbol = reader.read()) != -1) {
                        text.append((char)symbol);
                }
                text.append(('\n'));
            } catch (IOException e) {

                System.out.println("Не получилось прочитать содержимое файла\n");
            }
        }

        try(FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text.toString());
        }
        catch (IOException e){
            System.out.println("Не получилось записать содержимое файлов в другой файл\n");
        }
        System.out.println("Содержание файлов было записано в файл output.txt");
    }
}


