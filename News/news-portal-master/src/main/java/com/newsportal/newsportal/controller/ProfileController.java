package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.User;
import com.newsportal.newsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("profil")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public ModelAndView profilPage(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int userId = (int) session.getAttribute("id");
            modelAndView.addObject("user", userRepository.findById(userId).get());
            modelAndView.addObject("profileActive", "active");
            modelAndView.setViewName("profile.jsp");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping("update")
    public ModelAndView updateProfile(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phone-number");
        String address = request.getParameter("address");

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            Optional<User> user  = userRepository.findById((int)session.getAttribute("id"));
            user.get().setName(name).setLastname(lastname).setPhoneNumber(phoneNumber).setAddress(address);
            userRepository.saveAndFlush(user.get());
            modelAndView.setViewName("redirect:/");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }
}
