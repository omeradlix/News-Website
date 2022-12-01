package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.PostGroup;
import com.newsportal.newsportal.repository.PostGroupRepository;
import com.newsportal.newsportal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("haber")
public class SearchPostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostGroupRepository postGroupRepository;

    @GetMapping("filtre")
    public ModelAndView filterPosts(ModelAndView modelAndView, Model model, HttpSession session){
        String timeFilter = (String) model.asMap().get("timeFilter");
        List<String> postGroupFilter = (List<String>) model.asMap().get("postGroupFilter");
        List<PostGroup> postGroups = postGroupRepository.findAll();
        modelAndView.addObject("postGroups", postGroups);
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if( timeFilter != null && postGroupFilter != null ){
            modelAndView.addObject("timeFilter", timeFilter);
            modelAndView.addObject("postGroupFilter", postGroupFilter);
            List<Integer> postGroupIds = new ArrayList<>();
            for(String id: postGroupFilter)
                postGroupIds.add(Integer.parseInt(id));

            if(timeFilter.equals("dated"))
                if(isLoggedIn != null && isLoggedIn)
                    modelAndView.addObject("posts", postRepository.findAllPostsByPostGroupsAndPrivacyAscOrder(postGroupIds, Arrays.asList(true, false)));
                else
                    modelAndView.addObject("posts", postRepository.findAllPostsByPostGroupsAndPrivacyAscOrder(postGroupIds, Arrays.asList(false)));

            else
                if(isLoggedIn != null && isLoggedIn)
                    modelAndView.addObject("posts", postRepository.findAllPostsByPostGroupsAndPrivacyDescOrder(postGroupIds, Arrays.asList(true, false)));
                else
                    modelAndView.addObject("posts", postRepository.findAllPostsByPostGroupsAndPrivacyDescOrder(postGroupIds, Arrays.asList(false)));

        }else{
            modelAndView.addObject("postGroupFilter", postGroups.stream().map(PostGroup::getId).collect(Collectors.toList()));
            if(isLoggedIn != null && isLoggedIn)
                modelAndView.addObject("posts", postRepository.findAllPostsByPrivacyListDescOrder(Arrays.asList(true, false)));
            else
                modelAndView.addObject("posts", postRepository.findAllPostsByPrivacyListDescOrder(Arrays.asList(false)));


        }
        modelAndView.addObject("filterActive", "active");

        modelAndView.setViewName("searchPost.jsp");

        return modelAndView;
    }

    @PostMapping("filter")
    public ModelAndView doFilter(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String timeFilter = request.getParameter("time-filter");
        String [] postGroupFilter = request.getParameterValues("post-group-filter");

        redirectAttributes.addFlashAttribute("timeFilter", timeFilter);
        redirectAttributes.addFlashAttribute("postGroupFilter", Arrays.asList(postGroupFilter));

        modelAndView.setViewName("redirect:/haber/filtre");
        return modelAndView;
    }
}
