package com.rest.api.controller.membershipController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.model.Membership;
import com.rest.api.repositories.membershipRepo.MembershipRepository;

@RestController
public class MembershipController {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipController(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/allmemberships")
    public List<Membership> returnAllMemberships() {
        return membershipRepository.findAll();
    }
}
