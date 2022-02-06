package com.rest.api.repositories.clientRepo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.model.Client;

public interface ClientRepository extends JpaRepository<Client, String> {

    Page<Client> findByOrderByIdDesc(Pageable pageable);
    //Load all clients order by id desc
    List<Client> findByOrderByIdDesc();

    List<Client> findByNameStartsWith(String name);

    List<Client> findClientsByStatus(String status);

    List<Client> findAllByName(String name);

    List<Client> findAllByPhone(Long phone);

//    Optional<Client> findById(String id);

}
