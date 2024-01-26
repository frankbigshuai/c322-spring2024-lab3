package com.example.demo.repository;

import com.example.demo.model.AnimalData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class AnimalRepository {
    private static final String NEW_Line = System.lineSeparator();
    private static final String DATABASE_Name = "db.txt";
    private static void appendToFile(Path path, String content)
        throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public boolean add(AnimalData animalData) throws IOException{
        Path path = Paths.get(DATABASE_Name);
        String data = animalData.getName() + ","+
                animalData.getPicture() +
                "," + animalData.getLocation();
        appendToFile(path,data+ NEW_Line);
        return true;
    }
}
