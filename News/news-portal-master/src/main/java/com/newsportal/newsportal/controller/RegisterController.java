package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.Group;
import com.newsportal.newsportal.model.SecurityQuestion;
import com.newsportal.newsportal.model.User;
import com.newsportal.newsportal.model.UserAccount;
import com.newsportal.newsportal.repository.SecurityQuestionRepository;
import com.newsportal.newsportal.repository.UserAccountRepository;
import com.newsportal.newsportal.repository.UserRepository;
import com.newsportal.newsportal.source.Grp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;

    @GetMapping("kayit-ol")
    public ModelAndView registerPage(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("id") == null){
            modelAndView.addObject("securityQuestions", securityQuestionRepository.findAll());
            modelAndView.setViewName("register.jsp");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping("register")
    public ModelAndView register(ModelAndView modelAndView, HttpServletRequest request){
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone-number");
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String securityQuestionId = request.getParameter("security-question");
        String securityQuestionAnswer = request.getParameter("security-question-answer");
        if (!password1.equals(password2)){
            modelAndView.setViewName("redirect:/kayit-ol");
            return modelAndView;
        }


        User user = new User().setName(name).setLastname(lastname).setAddress(address).setPhoneNumber(phoneNumber).setBeInUse(true).setGroup(new Group().setId(Grp.USER));

        UserAccount userAccount  = new UserAccount()
                .setUsername(username)
                .setPassword(password1)
                .setSecurityQuestion(new SecurityQuestion().setId(Integer.parseInt(securityQuestionId)))
                .setSecurityQuestionAnswer(securityQuestionAnswer)
                .setUser(userRepository.saveAndFlush(user));
        userAccountRepository.saveAndFlush(userAccount);

        modelAndView.setViewName("redirect:/giris");
        return modelAndView;

    }



}
