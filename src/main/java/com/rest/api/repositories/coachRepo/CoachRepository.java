package com.rest.api.repositories.coachRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
