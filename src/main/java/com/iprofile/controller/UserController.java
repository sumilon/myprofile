package com.iprofile.controller;

import java.util.ArrayList;
import java.util.List;

import com.iprofile.model.UserRegistration;
import com.iprofile.repository.JdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private JdbcRepository jdbcRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("registration", "user", new UserRegistration());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute("user") UserRegistration userRegistrationObject) {

        log.debug("Register User : " + userRegistrationObject.getUsername());

        int count = jdbcRepository.checkUserExist(userRegistrationObject.getUsername());

        log.debug("Check for user already exist : "+count);

        if(count==0) {
            // authorities to be granted
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority(userRegistrationObject.getRole()));

            String encodedPassword = passwordEncoder().encode(userRegistrationObject.getPassword());

            User user = new User(userRegistrationObject.getUsername(), encodedPassword, authorities);
            jdbcUserDetailsManager.createUser(user);
            return new ModelAndView("redirect:/welcome");
        } else {
            return new ModelAndView("registration", "message", "User Already Registered");
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}
