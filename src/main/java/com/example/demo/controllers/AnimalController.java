package com.example.demo.controllers;

import com.example.demo.repository.AnimalRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
private AnimalRepository animalRepository;
public AnimalController(AnimalRepository animalRepository) {
    this.animalRepository=animalRepository;
}
