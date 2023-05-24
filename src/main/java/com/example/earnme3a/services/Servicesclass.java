package com.example.earnme3a.services;



import java.util.List;

import com.example.earnme3a.Repos.DriverRepository;
import com.example.earnme3a.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.earnme3a.Repos.DriverRepository;
import com.example.earnme3a.Repos.Signupreposi;
import com.example.earnme3a.models.Driver;
import com.example.earnme3a.models.signup;


@Service
public class Servicesclass implements Services{

	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private Signupreposi signupreposi;
	@Override
	public Driver savedriver(Driver driver) {
		
	
		return driverRepository.save(driver);
	}

	@Override
	public List<Driver> getAlldrivers() {
		return driverRepository.findAll();
	}

//	@Override
//	public List<Driver> getAlldrivers() {
//		// TODO Auto-generated method stub
//		return driverRepository.findAll();
//	}
	@Override
	public Driver updateUser(Driver driver) {
		// TODO Auto-generated method stub
 
		Driver existingUser = driverRepository.findById(driver.getId()).get();
		existingUser.setFname(driver.getFname());
		existingUser.setLname(driver.getLname());
		existingUser.setTelephone(driver.getTelephone());
		existingUser.setLicenseId(driver.getLicenseId());
		existingUser.setAccidentPicture(driver.getAccidentPicture());
		existingUser.setEmail(driver.getEmail());
		existingUser.setPassword(driver.getPassword());
		
        Driver updatedUser = driverRepository.save(existingUser);
        return updatedUser;
		
 }

//	@Override
//	public signup SignupAccount(signup account) {
//		return null;
//	}

		@Override
	public signup SignupAccount(signup account) {
		// TODO Auto-generated method stub
		return signupreposi.save(account);
	}
	@Override
	public Page<Driver> getPaginated(int pageNo, int pageSize) {
		PageRequest pageable= PageRequest.of(pageNo-1,pageSize);
		return this.driverRepository.findAll(pageable);
		
	}



	
}
