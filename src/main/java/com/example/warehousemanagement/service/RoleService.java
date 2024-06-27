package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Role;
import com.example.warehousemanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }
}
