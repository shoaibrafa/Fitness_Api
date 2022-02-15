package com.rest.api.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.repositories.clientMembershipRepo.ClientMembershipRepository;
import com.rest.api.repositories.personalTrainingRepo.PersonalTrainingRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DashboardRestController {
	
	private ClientMembershipRepository clientMembershipRepository;
	private PersonalTrainingRepository personalTrainingRepository;
	
	@Autowired
	DashboardRestController(ClientMembershipRepository clientMembershipRepository,
							PersonalTrainingRepository personalTrainingRepository){
		this.clientMembershipRepository = clientMembershipRepository;
		this.personalTrainingRepository = personalTrainingRepository;
	}
	
	/**
	 * The following method returns the total membership balance of all clients
	 * @return
	 */
	 @CrossOrigin(origins = "*")
	 @GetMapping("/dashboard/membalance")
	public Long totalMembershipsBalance() {
		Long total = this.clientMembershipRepository.sumClientMembershipBalance();
		return total;
	}
	 
	/**
	 * The following method returns the total personal training balance from
	 * all clients. On the front end the result is displayed on the dash board.
	 */
	 @CrossOrigin(origins = "*")
	 @GetMapping("/dashboard/ptbalance")
	 public Double totalPersonalTrainingBalance() {
		 Double total = this.personalTrainingRepository.sumClientPersonalTrainingBalance();
		 return total;
	 }
	 
}
