package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.PostGroup;
import com.newsportal.newsportal.repository.PermissionRepository;
import com.newsportal.newsportal.repository.PostGroupRepository;
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
import java.util.Optional;

@Controller
@RequestMapping("kategori")
public class PostGroupController {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PostGroupRepository postGroupRepository;

    @GetMapping("listele")
    public ModelAndView allPostGroups(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_GROUP, groupId)){
                modelAndView.addObject("postGroups", postGroupRepository.findAll());
                modelAndView.addObject("categoryActive", "active");

                modelAndView.setViewName("allPostGroups.jsp");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView createPostGroup(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_GROUP, groupId)) {
                String name = request.getParameter("category-name");
                String desc = request.getParameter("category-description");
                postGroupRepository.saveAndFlush(new PostGroup().setName(name).setDescription(desc));
                modelAndView.setViewName("redirect:/kategori/listele");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }


    @PostMapping("delete/{postId}")
    public ModelAndView deletePostGroup(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_GROUP, groupId)) {
                Optional<PostGroup> postGroup = postGroupRepository.findById(postId);
                if(postGroup.isPresent()){
                    postGroupRepository.delete(postGroup.get());
                    modelAndView.setViewName("redirect:/kategori/listele");
                }else{
                    modelAndView.setViewName("redirect:/kategori/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }

    @PostMapping("edit/{postId}")
    public ModelAndView editPostGroup(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId, HttpServletRequest request){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_DELETE_UPDATE_POST_GROUP, groupId)) {
                Optional<PostGroup> postGroup = postGroupRepository.findById(postId);
                if(postGroup.isPresent()) {
                    String name = request.getParameter("category-name");
                    String desc = request.getParameter("category-description");
                    postGroupRepository.saveAndFlush(postGroup.get().setName(name).setDescription(desc));
                    modelAndView.setViewName("redirect:/kategori/listele");
                }else{
                    modelAndView.setViewName("redirect:/kategori/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }
}
