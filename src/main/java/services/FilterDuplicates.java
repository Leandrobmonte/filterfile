package services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilterDuplicates {
    String destPath;
    String sourcePath;

    public FilterDuplicates() {
    }

    public void filter(String source, String destiny) throws Exception {
        destPath = destiny;
        sourcePath = source;
        File directory;
        File destDir;
        try {
            directory = new File(sourcePath);
            destDir = new File(destPath);
        }catch (Exception e){
            throw new Exception("Campo de origem não preenchido");
        }

        // get all the files in the directory
        File[] files = directory.listFiles();

        // loop through the files and print their names
        List<File> convertidos = new ArrayList<>();
        int count = 0;
        for (File file : files) {
            String extension1 = getExtension(file);
            String nameFile1 = getNamewithoutExtension(file);
            for (File fileComparing : files){
                String extension2 = getExtension(fileComparing);
                String nameFile2 = getNamewithoutExtension(fileComparing);

                if(nameFile1.equals(nameFile2) && !extension1.equals(extension2)){
                    count ++;
                    convertidos.add(fileComparing);
                }
            }
        }
        System.out.println("Itens Convertidos: "+ count );
        moveFiles(convertidos, destDir);
    }
    public String getExtension(File file){
        String name = file.getName();
        int lastIndexOfDot = name.lastIndexOf('.');
        String extension = "";
        if (lastIndexOfDot > 0 && lastIndexOfDot < name.length() - 1) {
            extension = name.substring(lastIndexOfDot + 1);
        }
        return extension;
    }
    public String getNamewithoutExtension(File file){
        String name = file.getName();
        String[] parts = name.split("\\.");
        String extension = "";
        if (parts.length > 1) {
            extension = parts[parts.length - 1];
            name = name.substring(0, name.length() - extension.length() - 1);
        }
        return name;
    }

    public void moveFiles(List<File> filesConvertidos, File destDir){

        //all the files in the source directory
        File[] files = filesConvertidos.toArray(new File[filesConvertidos.size()]);

        // create a list of source and destination paths
        List<Path> sourcePaths = new ArrayList<>();
        List<Path> destPaths = new ArrayList<>();
        for (File file : files) {
            Path sourcePath = Paths.get(file.getAbsolutePath());
            Path destPath = Paths.get(destDir.getAbsolutePath(), file.getName());
            sourcePaths.add(sourcePath);
            destPaths.add(destPath);
        }

        // move the files to the destination directory
        for (int i = 0; i < sourcePaths.size(); i++) {
            try {
                Files.move(sourcePaths.get(i), destPaths.get(i));
            } catch (Exception e) {
                System.out.println("Failed to move file: " + e.getMessage());
            }
        }
    }
}
