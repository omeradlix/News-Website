package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.repository.SecurityQuestionRepository;
import com.newsportal.newsportal.repository.UserAccountRepository;
import com.newsportal.newsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("giris")
    public ModelAndView loginPage(ModelAndView modelAndView, HttpSession session, Model model){
        if(session.getAttribute("id") == null){
            modelAndView.setViewName("signin.jsp");
            modelAndView.addObject("wrongUsernameOrPassword", model.asMap().get("wrongUsernameOrPassword"));
        } else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
    @PostMapping("login")
    public ModelAndView loginto(HttpServletRequest request, ModelAndView modelAndView, HttpSession session, RedirectAttributes redirectAttributes){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer id = userAccountRepository.findUserIdByUsernameAndPassword(username, password);

        if(id != null){
            session.setAttribute("id", id);
            session.setAttribute("groupId", userRepository.findGroupIdByUserId(id));
            session.setAttribute("isLoggedIn", true);
            modelAndView.setViewName("redirect:/");
        }else{
            redirectAttributes.addFlashAttribute("wrongUsernameOrPassword", true);
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }
}
