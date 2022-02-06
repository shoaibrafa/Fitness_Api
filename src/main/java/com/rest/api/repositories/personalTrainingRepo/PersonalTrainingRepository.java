package com.rest.api.repositories.personalTrainingRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.model.PersonalTraining;

import java.util.List;

public interface PersonalTrainingRepository extends JpaRepository<PersonalTraining, Integer> {
    List<PersonalTraining> findPersonalTrainingByClient_Id(String id);
}
