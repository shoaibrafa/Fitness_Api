package com.rest.api.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.api.security.dao.UserDao;
import com.rest.api.security.model.Role;
import com.rest.api.security.model.User;
import com.rest.api.security.model.UserDto;
import com.rest.api.security.service.RoleService;
import com.rest.api.security.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private RoleService roleService;
    private UserDao userDao;
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(RoleService roleService,
                           UserDao userDao,
                           BCryptPasswordEncoder bcryptEncode
                           ){
        this.roleService = roleService;
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        if(nUser.getRoles().contains("admin")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }
        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }
}
