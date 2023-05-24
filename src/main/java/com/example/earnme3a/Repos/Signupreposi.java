package com.example.earnme3a.Repos;

import com.example.earnme3a.models.signup;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.earnme3a.models.Driver;
import com.example.earnme3a.models.signup;

public interface Signupreposi extends JpaRepository<signup, Integer>{
	signup findByEmail(String email);
}
