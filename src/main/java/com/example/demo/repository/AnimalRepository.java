package com.example.demo.repository;

import com.example.demo.model.AnimalData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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
    public List<AnimalData> findAll() throws IOException{
        List<AnimalData> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_Name);
        List<String> data = Files.readAllLines(path);
        for (String line : data){
            String[] words = line.split(",");
            AnimalData a = new AnimalData();
            a.setName(words[0]);
            a.setPicture(words[1]);
            a.setLocation(words[2]+","+words[3]);
            result.add(a);
        }
        return result;
    }

    @GetMapping("/search")
    public List<AnimalData> Search (@RequestParam String name,
                                    @RequestParam String picture,
                                    @RequestParam String location)
    {
        try{
            System.out.println(name);
            System.out.println(picture);
            System.out.println(location);
            return animalRepository.find(name,picture,location);
        } catch(IOException e){
            return null;
        }
    }
}
