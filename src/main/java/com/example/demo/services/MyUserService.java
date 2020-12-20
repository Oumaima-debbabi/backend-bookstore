package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.security.*;
import com.example.demo.entities.Command;
import com.example.demo.entities.MyUserDetails;
import com.example.demo.entities.User;
import  com.example.demo.repository.UserRepository;
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
   UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    CommandService commandService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user= userRepository.getUserByUserName(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("No user with the username "+userName));
        // return user.map(MyUserDetails ::new).get();
        return new MyUserDetails(user.get());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No " +
                "user found with the ID "+ id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user) throws Exception {
        if (userRepository.getUserByUserName(user.getUserName()).isPresent()) throw new Exception("This " +
                "username " +
                "already exist");
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setActive(true);
        return userRepository.save(user);
    }

    public User addAdmin(User user) throws Exception {
        if (userRepository.getUserByUserName(user.getUserName()).isPresent()) throw new Exception("This username " +
                "already exist");
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("ROLE_ADMIN");
        user.setActive(true);
        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        User utl=
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                        "No user found with a matching ID: "+userId));
        List<Command> c= commandService.getUserCommands(userId);
        for (Command com: c)
            commandService.deleteCommand(com.getId());
        userRepository.deleteById(userId);
        return utl;
    }

    public User updateUserName(Long userId, User user){
        User u = getUserById(userId);
        u.setUserName(user.getUserName());
        return userRepository.save(u);
    }


}
