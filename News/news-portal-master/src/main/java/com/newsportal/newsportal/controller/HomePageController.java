package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public ModelAndView modelAndView(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn)
            modelAndView.addObject("posts", postRepository.findLast5PostsOrderByDescCreated_At());
        else
            modelAndView.addObject("posts", postRepository.findLast5PostsOrderByPrivacyDescCreated_At(false));


        modelAndView.addObject("homeActive", "active");
        modelAndView.setViewName("home.jsp");
        return modelAndView;
    }
}
