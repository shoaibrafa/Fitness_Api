package com.rest.api.controller.coachController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.model.Coach;
import com.rest.api.repositories.coachRepo.CoachRepository;

@RestController
public class CoachRestController {

    private CoachRepository coachRepository;

    @Autowired
    public CoachRestController(CoachRepository coachRepository){
        this.coachRepository = coachRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/restcoaches/loadcoaches")
    public List<Coach> getCoaches(){
        List<Coach> coaches = coachRepository.findAll();
        return coaches;
    }
}
