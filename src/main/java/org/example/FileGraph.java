package org.example;


import java.util.*;

public class FileGraph {
    public HashMap<String, List<String>> adjList;
    public ArrayList<String> sorted = new ArrayList<>();

    FileGraph() {
        adjList = new HashMap<>();
    }


    /**
     * Добавление вершины
     * @param filePath Путь к файлу
     */
    public void addVertex(String filePath) {
        adjList.putIfAbsent(filePath, new ArrayList<>());
    }

    /**
     * Добавление грани
     * @param parentFile Родительский путь
     * @param file Зависимый путь
     */
    public void addEdge(String parentFile, String file) {
        adjList.get(parentFile).add(file);
    }

    /**
     * Сортировка файлов всего графа
     */
    public void sortFiles() {
        ArrayList<String> visited = new ArrayList<>();
        for (String key : adjList.keySet()) {
            if (!visited.contains(key)) {
                sortFile(key, visited);
            }
        }

    }

    /**
     * Рекурсивный метод сортировки
     * @param key Имя файла
     * @param visited Список посещенных файлов
     */
    public void sortFile(String key, ArrayList<String> visited) {
        if (visited.contains(key)) {
            return;
        }
        visited.add(key);
        if(adjList.get(key)!=null) {
            for (String relation : adjList.get(key)) {
                sortFile(relation, visited);
            }
        }
        sorted.add(key);
    }

    /**
     * Проверка всего графа на циклы
     * @return Список зацикливающихся файлов
     */
    public ArrayList<String> detectCycle() {
        for (String key : adjList.keySet()) {
            ArrayList<String> visited = new ArrayList<>();
            if (detectCycle(key, visited) != null) {
                return visited;
            }
        }
        return null;
    }

    /**
     * Рекурсивная проверка на цикл
     * @param key Название файла
     * @param visited Список посещенных файлов
     * @return Список посещенных файлов
     */
    public ArrayList<String> detectCycle(String key, ArrayList<String> visited) {
        if (visited.contains(key)) {
            return visited;
        }
        visited.add(key);
        if (adjList.get(key) != null) {
            for (String relation : adjList.get(key)) {
                if (detectCycle(relation, visited) != null) {
                    return visited;
                }
                visited.clear();
                visited.add(key);
            }
        }
        return null;

    }
}
