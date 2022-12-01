package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.*;
import com.newsportal.newsportal.repository.*;
import com.newsportal.newsportal.source.Grp;
import com.newsportal.newsportal.source.Perm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("editor")
public class EditorController {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;
    @Autowired
    private PostGroupRepository postGroupRepository;


    @GetMapping("listele")
    public ModelAndView allEditors(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_EDITOR, groupId)){
                List<User> editors = userRepository.findUsersByGroupId(Grp.EDITOR);
                modelAndView.addObject("editors", editors);
                modelAndView.addObject("securityQuestions", securityQuestionRepository.findAll());
                modelAndView.addObject("postGroups", postGroupRepository.findAll());
                modelAndView.addObject("editorActive", "active");
                modelAndView.setViewName("allEditors.jsp");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }

        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView createEditors(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_EDITOR, groupId)) {
                String name = request.getParameter("editor-name");
                String lastname = request.getParameter("editor-lastname");
                String address = request.getParameter("editor-address");
                String phoneNumber = request.getParameter("editor-phone-number");
                String username = request.getParameter("editor-username");
                String password1 = request.getParameter("editor-password1");
                String password2 = request.getParameter("editor-password2");
                String securityQuestionId = request.getParameter("editor-security-question");
                String securityQuestionAnswer = request.getParameter("editor-security-question-answer");
                String[] postGroups = request.getParameterValues("editor-post-groups");
                if (!password1.equals(password2)){
                    modelAndView.setViewName("redirect:/editor/listele");
                    return modelAndView;
                }
                List<PostGroup> postGroupListForUser = new ArrayList<>();
                for(String group: postGroups)
                    postGroupListForUser.add(new PostGroup().setId(Integer.parseInt(group)));


                User user = new User().setName(name).setLastname(lastname).setAddress(address).setPhoneNumber(phoneNumber).setGroup(new Group().setId(Grp.EDITOR)).setPostGroup(postGroupListForUser);
                userRepository.saveAndFlush(user);
                UserAccount userAccount = new UserAccount().setUsername(username).setPassword(password1)
                        .setSecurityQuestion(new SecurityQuestion().setId(Integer.parseInt(securityQuestionId)))
                        .setSecurityQuestionAnswer(securityQuestionAnswer)
                        .setUser(user);
                userAccountRepository.saveAndFlush(userAccount);
                modelAndView.setViewName("redirect:/editor/listele");

            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }

    @PostMapping("delete/{editorId}")
    public ModelAndView deleteEditor(ModelAndView modelAndView, HttpSession session, @PathVariable("editorId")final int editorId){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_EDITOR, groupId)) {
                Optional<UserAccount> userAccount = userAccountRepository.findUserAccountByUserId(editorId);
                if(userAccount.isPresent()){
                    userRepository.delete(userAccount.get().getUser());
                    userAccountRepository.delete(userAccount.get());
                    modelAndView.setViewName("redirect:/editor/listele");
                }else{
                    modelAndView.setViewName("redirect:/editor/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/editor/listele");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }

        return modelAndView;
    }

}
