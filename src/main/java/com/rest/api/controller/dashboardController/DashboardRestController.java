package com.rest.api.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.repositories.clientMembershipRepo.ClientMembershipRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DashboardRestController {
	
	private ClientMembershipRepository clientMembershipRepository;
	
	@Autowired
	DashboardRestController(ClientMembershipRepository clientMembershipRepository){
		this.clientMembershipRepository = clientMembershipRepository;
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
}
