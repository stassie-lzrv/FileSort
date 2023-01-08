package org.example;


import java.util.*;

public class FileGraph {
    private Map<FileStruct, List<FileStruct>> vertices;


    //конструкторы, геттеры и сеттеры

    public Set<FileStruct> getVertices(){
        return vertices.keySet();
    }

    // метод для добавления вершины
    public void addVertex(FileStruct file){
        vertices.putIfAbsent(file, new ArrayList<>());
    }

    // метод для добавления зависимости(грани)
    public void addEdge(FileStruct parentFile, FileStruct file){
        vertices.get(parentFile).add(file);
    }

}
