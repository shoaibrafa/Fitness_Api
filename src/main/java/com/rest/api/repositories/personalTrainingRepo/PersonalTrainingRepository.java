package com.rest.api.repositories.personalTrainingRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.api.model.PersonalTraining;

import java.util.List;

public interface PersonalTrainingRepository extends JpaRepository<PersonalTraining, Integer> {
	/**
	 * This method returns all Personal Training a client has taken during his 
	 * memberships.
	 * @param id is the client id based on which the method loads all his personal trainings
	 * @return returns a list of all personal trainings
	 */
    List<PersonalTraining> findPersonalTrainingByClient_Id(String id);
    
    
    
    /**
     * This method calculates all personal training balances from all clients
     * @return
     */
    @Query(value = "SELECT SUM(balance) FROM personal_training", nativeQuery = true)
    Double sumClientPersonalTrainingBalance();
}
