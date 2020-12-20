package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Command;
import com.example.demo.services.CommandService;

@RestController
@RequestMapping("/command/")
public class CommandRestController {

    @Autowired
    CommandService commandService;

    @GetMapping("all")
    public List<Command> getAllCommand(){
        return commandService.getAllCommand();
    }

    @GetMapping("{id}")
    public Command getCommandById(@PathVariable Long id) {
        return commandService.getCmdById(id);
    }

    @GetMapping("user/{id}")
    public List<Command> getCommandByUser(@PathVariable Long id) {
        return commandService.getUserCommands(id);
    }

    @PostMapping("new")
    public Command createCommand(@RequestBody @Validated Command c) {
        return commandService.createCommand(c);
    }

    @DeleteMapping("{id}")
    public Command deleteCommand(@PathVariable Long id) {
        return commandService.deleteCommand(id);
    }

    @GetMapping("{id}/total")
    public double windUpCommand(@PathVariable Long id){
        return commandService.windUpCommand(id);
    }
}
