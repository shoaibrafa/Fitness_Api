package com.rest.api.repositories.clientMembershipRepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.api.model.Client_Membership;

import java.util.List;

public interface ClientMembershipRepository extends JpaRepository<Client_Membership, Long> {
    /**
     * A method to return list of client_membership based as the client id.
     */
    List<Client_Membership> findClient_MembershipByClient_Id(String id);

    /**
     * This method runs a custom query to retrieve all clients_membership based on the max date.
     * This method is used to check which client has active membership and which clients' memberships
     * are expired.
     */
    @Query(value = "SELECT * FROM client_membership as cm\n" +
            "WHERE end_date = (\n" +
            "    SELECT MAX(end_date)\n" +
            "    FROM client_membership AS cm2\n" +
            "    WHERE cm.client_id = cm2.client_id and\n" +
            "          cm.client_id = ?1)",nativeQuery = true)
    Client_Membership  findClient_MembershipByEnd_date(String id);

    /**
     * This method is used to create a new client_membership.
     */
    Client_Membership save(Client_Membership client_membership);

}
