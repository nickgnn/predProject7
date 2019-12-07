package ru.javamentor.predProject7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.javamentor.predProject7.entities.Role;
import ru.javamentor.predProject7.entities.User;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.repository.UserRepository;
import ru.javamentor.predProject7.service.RoleService;
import ru.javamentor.predProject7.service.UserService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> getAllUsers() throws DBException {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String name) throws DBException {
        return userRepository.findByUsername(name).get();
    }

    @Override
    public Long getUserIdByName(String name) throws DBException {
        return userRepository.findIdByUsername(name).get().getId();
    }

    @Override
    public void addUser(User user) throws DBException {
        Long role_ID = 0L;

        if (StringUtils.isEmpty(user.getAge())) {
            user.setAge(0);
        }

        if (StringUtils.isEmpty(user.getRole())) {
            user.setRole("user");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole().toUpperCase());

//        if (isExistsUser(name)) {
//            System.out.println("This Username already exists, please choose another name!");
//            throw new DBException(new Exception("This Username already exists, please choose another name!"));
//        }

        if (user.getRole().contains("ADMIN")) {
            user.setRole("ROLE_ADMIN");
            role_ID = roleService.getRoleIdByName(user.getRole());
        }

        if (user.getRole().contains("USER")) {
            user.setRole("ROLE_USER");
            role_ID = roleService.getRoleIdByName(user.getRole());
        }

        user.setRole_id(role_ID);

        userRepository.saveAndFlush(user);
        roleService.addRoles(getUserIdByName(user.getUsername()), role_ID);
    }

    @Override
    public void deleteUserById(Long id) throws DBException {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean isExistsUser(String name) throws DBException {
        try {
            return getUserByName(name) != null;
        } catch (NoSuchElementException e) {
            e.getMessage();

            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = getUserByName(username);
        } catch (DBException e) {
            e.getMessage();
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
