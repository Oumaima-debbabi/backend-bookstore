package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Command;


public interface CommandRepository extends JpaRepository<Command, Long> {
    public List<Command> getCommandsByCreationDate(Date cmdDate);

    public Optional<Command> findCommandByUserIdAndAndWindedUp(Long userId, boolean windedUp);

    public List<Command> getCommandsByUserId(Long userId);
}
