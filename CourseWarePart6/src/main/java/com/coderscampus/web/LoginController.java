package com.coderscampus.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;

@Controller
public class LoginController
{
  UserRepository userRepo;
  
  @RequestMapping(value="login", method=RequestMethod.GET)
  public String login ()
  {
    return "login";  
  }
  
  @RequestMapping(value="register", method=RequestMethod.GET)
  public String register (ModelMap model)
  {
    User user = new User();
    model.put("user", user);
    
    return "register";
  }
  
  @RequestMapping(value="register", method=RequestMethod.POST)
  public String registerPost (@ModelAttribute User user, ModelMap model)
  {
    User userByEmail = userRepo.findUserByEmail(user.getEmail());
    if (userByEmail == null)
    {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      userRepo.save(user);
      return "redirect:/login";
    }
    else
    {
      model.put("errorMsg", "Error: This user account already exists.");
      return "register";
    }
    
  }

  @Autowired
  public void setUserRepo(UserRepository userRepo)
  {
    this.userRepo = userRepo;
  }
  
  @Test
  public void encoding_test()
  {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String pwd = "user";
    System.out.println(encoder.encode(pwd));
  }
  
}
