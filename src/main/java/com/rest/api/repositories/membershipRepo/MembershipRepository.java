package com.rest.api.repositories.membershipRepo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.api.model.Membership;
import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, String> {

    List<Membership> findAll();

    Membership findByType(String membership_id);
}
