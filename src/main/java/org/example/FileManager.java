package org.example;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.Objects;

public class FileManager {
    private final FileGraph fileGraph = new FileGraph();
    private final String rootDirectory;

    FileManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        getFiles();
        setRelations();
    }

    public void getFiles(){
        File dir = new File(rootDirectory); //path указывает на директорию
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                fileGraph.addVertex(new FileStruct(file.getAbsolutePath()));
            }
        }
    }

    public void setRelations(){
        for(FileStruct file : fileGraph.getVertices()){
            for (String line : file.getText()){
                if(line.contains("require")){
                    String childPath = rootDirectory+ FileSystems.getDefault().getSeparator()+line.substring(9,line.length()-1);
                    fileGraph.addEdge(file, new FileStruct(childPath));
                }
            }
        }

    }

    //print results (топологическая сортировка?? + обработать циклы)

}
