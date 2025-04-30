package com.example.cleanshelf.service;


import com.example.cleanshelf.model.Admin;
import com.example.cleanshelf.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository,PasswordEncoder passwordEncoder){
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Admin register(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Optional<Admin> authenticate(String email, String rawPassword){
        return adminRepository.findByEmail(email)
                .filter(admin -> passwordEncoder.matches(rawPassword, admin.getPassword()));
    }



}
