package com.rest.api.controller.personalTrainingController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.model.PersonalTraining;
import com.rest.api.repositories.personalTrainingRepo.PersonalTrainingRepository;



@RestController
public class PersonalTrainingRestController {
    /**
     * Creating the repository field
     */
    private PersonalTrainingRepository personalTrainingRepository;

    /**
     * Autowiring the repository field via constructor.
     */
    @Autowired
    public PersonalTrainingRestController(PersonalTrainingRepository personalTrainingRepository){
        this.personalTrainingRepository = personalTrainingRepository;
    }

    /**
     * Returns all the personal training information of a client based on the client id.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/restclients/pt/{id}")
    public List<PersonalTraining> getPersonalTrainings(@PathVariable("id") String id){
       List<PersonalTraining> pt = personalTrainingRepository.findPersonalTrainingByClient_Id(id);
       return pt;
    }

    /**
     * Calculates the total available personal training balance based on the clients id.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "restclients/ptbalance/{id}")
    public int getPtBalance(@PathVariable("id") String id){
        int balance = 0;
        List<PersonalTraining> pt = personalTrainingRepository.findPersonalTrainingByClient_Id(id);

        for (PersonalTraining p: pt){
            balance = balance + p.getBalance();
        }
        return balance;
    }
}
