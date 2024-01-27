package com.example.demo.controllers;

import com.example.demo.model.AnimalData;
import com.example.demo.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping
    public boolean add(@RequestBody AnimalData data) {
        try {
            return animalRepository.add(data);
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping
    public List<AnimalData> findAll() {
        try {
            return animalRepository.findAll();
        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping
    public List<AnimalData> find(String name, String picture, String location) throws IOException
    {
        List<AnimalData> result = new ArrayList<>();
        List<AnimalData> allAnimals= findAll();
        for(AnimalData animal: allAnimals)
        {
            if ((name==null||animal.getName().equals(name))&&
            (picture==null||animal.getPicture().equals(picture))&&
                    (location==null||animal.getLocation().equals(location)))
            {
                result.add(animal);
            }

        }

        return result;
    }

}



