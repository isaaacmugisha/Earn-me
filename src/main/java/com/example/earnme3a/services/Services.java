package com.example.earnme3a.services;

import java.security.Signature;
import java.util.List;

import com.example.earnme3a.models.Driver;
import org.springframework.data.domain.Page;

import com.example.earnme3a.Repos.Signupreposi;
import com.example.earnme3a.models.Driver;
import com.example.earnme3a.models.signup;

public interface Services {
	
	public Driver savedriver(Driver driver);
	
	public List<Driver> getAlldrivers();
	public Driver updateUser(Driver driver);
	public signup SignupAccount(signup account);
	public Page<Driver> getPaginated(int pageNo,int pageSize);

}
