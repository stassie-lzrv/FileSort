package org.example;


import java.util.*;

public class FileGraph {
    public HashMap<String, List<String>> adjList;
    public ArrayList<String> sorted = new ArrayList<>();

    FileGraph(){
        adjList = new HashMap<>();
    }


    // метод для добавления вершины
    public void addVertex(String filePath){
        adjList.putIfAbsent(filePath, new ArrayList<>());
    }

    // метод для добавления зависимости(грани)
    public void addEdge(String parentFile, String file){
        adjList.get(parentFile).add(file);
    }

    public void sortFiles(){
        ArrayList<String> visited = new ArrayList<>();
        for (String key : adjList.keySet()){
            if(!visited.contains(key)){
                sortFile(key,visited);
            }
        }

    }
    public void sortFile(String key,ArrayList<String> visited){
        if(visited.contains(key)){
            return;
        }
        visited.add(key);
        for(String relation : adjList.get(key)){
            sortFile(relation,visited);
        }
        sorted.add(key);
    }

    public ArrayList<String> detectCycle(){
        for (String key : adjList.keySet()){
            ArrayList<String> visited = new ArrayList<>();
            if(detectCycle(key,visited)!=null){
                return visited;
            }
        }
        return null;
    }

    public ArrayList<String> detectCycle(String key,ArrayList<String> visited){
        if(visited.contains(key)){
            return visited ;
        }
        visited.add(key);
        for (String relation : adjList.get(key)){
            if(detectCycle(relation,visited)!=null){
                return visited;
            }
            visited.clear();
            visited.add(key);
        }
        return null;
    }
}
