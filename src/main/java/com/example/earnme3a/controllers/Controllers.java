package com.example.earnme3a.controllers;

import java.util.List;
import java.util.Optional;

import com.example.earnme3a.Repos.DriverRepository;
import com.example.earnme3a.Repos.Signupreposi;
import com.example.earnme3a.models.Driver;
import com.example.earnme3a.models.signup;
import com.example.earnme3a.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.earnme3a.Repos.DriverRepository;
import com.example.earnme3a.Repos.Signupreposi;
import com.example.earnme3a.models.Driver;
import com.example.earnme3a.models.signup;
import com.example.earnme3a.services.Services;


@Controller
public class Controllers {
	
	@Autowired
	private Services services;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private Signupreposi signupreposi;
	@RequestMapping("/")
	public String home() {
		
		return "signup";
	}
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/save")
	public String createdriver(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("licenseId") String licenseId,@RequestParam("accidentPicture")String accidentPicture, @RequestParam("telephone") String telephone, @RequestParam("email") String email, @RequestParam("password") String password  ) {
		 
		Driver driverusers=new Driver();
		driverusers.setFname(firstname);
		driverusers.setLname(lastname);
		driverusers.setTelephone(telephone);
		driverusers.setLicenseId(licenseId);
		driverusers.setAccidentPicture(accidentPicture);
		driverusers.setEmail(email);
		driverusers.setPassword(password);
		services.savedriver(driverusers);
		
		
		return "redirect:/view";
		
	}

	@GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        
        Optional<Driver> user = driverRepository.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("update");
        mav.addObject("allusers",user);

        return mav;
    }
	@RequestMapping("/view")
    public  ModelAndView homeafter(){
        return PageShow(1);
    }
	@PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id,Driver user) {
        Driver userAttributes=new Driver();
        userAttributes.setId(user.getId());
        userAttributes.setFname(user.getFname());
        userAttributes.setLname(user.getLname());
        userAttributes.setTelephone(user.getTelephone());
		userAttributes.setLicenseId(user.getLicenseId());
		userAttributes.setAccidentPicture(user.getAccidentPicture());
        userAttributes.setEmail(user.getEmail());
        userAttributes.setPassword(user.getPassword());
        Driver updatedUser = services.updateUser(userAttributes);
        return "redirect:/view";
    }
	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        Driver user = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        driverRepository.delete(user);
        return "redirect:/view";
    }
	@RequestMapping("/confirm")
	public String SignupAccount (@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword) {
		signup newdriver=new signup();
		newdriver.setEmail(email);
		newdriver.setPassword(password);
		newdriver.setConfirmPassword(confirmpassword);
		services.SignupAccount(newdriver);
		return "login";
	}
	

	@RequestMapping("/loginaction")
	public String SignupAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
	signup user = null;
	
	try {
		user = signupreposi.findByEmail(email);
				
	}catch (Exception e) {
		System.out.println(e);
	}
	if(user!=null &&(user.getEmail().equals(email)&& user.getPassword().equals(password))) {
		return "information";

		}else{
			return "login";
		}
	}
	@GetMapping("/page/{pageNo}")
    public  ModelAndView PageShow(@PathVariable (value = "pageNo") int pageNo){
        ModelAndView mav=new ModelAndView();
        int pageSize=5;
        Page<Driver> page=services.getPaginated(pageNo,pageSize);
        List<Driver> allusersdata=page.getContent();
        mav.setViewName("viewform");
        mav.addObject("currentPage",pageNo);
        mav.addObject("totalPages",page.getTotalPages());
        mav.addObject("totalItems",page.getTotalElements());
        mav.addObject("displayAll",allusersdata);
        return  mav;
    }
}
