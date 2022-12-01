package com.newsportal.newsportal.controller;

import com.newsportal.newsportal.model.Post;
import com.newsportal.newsportal.model.PostGroup;
import com.newsportal.newsportal.model.User;
import com.newsportal.newsportal.repository.PermissionRepository;
import com.newsportal.newsportal.repository.PostGroupRepository;
import com.newsportal.newsportal.repository.PostRepository;
import com.newsportal.newsportal.repository.UserRepository;
import com.newsportal.newsportal.source.Perm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("haber")
public class PostController {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;



    @GetMapping("yeni")
    public ModelAndView newPostPage(ModelAndView modelAndView, HttpSession session, Model model){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int userId =(int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_POST, groupId)){
                Boolean bool = (Boolean) model.asMap().get("updatePost");
                if(bool != null && bool){
                    modelAndView.addObject("updatePost", true);
                    modelAndView.addObject("post", model.asMap().get("post"));
                }
                modelAndView.addObject("newPostActive", "active");
                modelAndView.addObject("groups", userRepository.findPostGroupsByUserId(userId));
                modelAndView.setViewName("newPost.jsp");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }

    @PostMapping("create")
    public ModelAndView createPost(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int userId = (int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_POST, groupId)) {
                String title = request.getParameter("title");
                String postGroup = request.getParameter("post-group");
                String content = request.getParameter("content");
                String imgUrl = request.getParameter("img-url");
                String privacy = request.getParameter("privacy");
                boolean privacyBool = true;
                if(privacy != null && privacy.equals("on"))
                    privacyBool = false;


                postRepository.saveAndFlush(
                        new Post().setAuthor(new User().setId(userId))
                        .setVerified(false)
                        .setTitle(title)
                        .setPostGroup(new PostGroup().setId(Integer.parseInt(postGroup)))
                        .setContent(content)
                        .setImageUrl(imgUrl)
                        .setPrivacy(privacyBool)
                );
                modelAndView.setViewName("redirect:/haber/listele");
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("{postId}")
    public ModelAndView postPage(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId, Model model){
        Post redirectedPost = (Post) model.asMap().get("post");
        if(redirectedPost != null){
            modelAndView.addObject("approve_post_permission", model.asMap().get("approve_post_permission"));
            modelAndView.addObject("post", redirectedPost);
            modelAndView.setViewName("post.jsp");
        }else{
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent() && post.get().isVerified()){
                modelAndView.addObject("post", post.get());
                modelAndView.setViewName("post.jsp");
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }

        return modelAndView;
    }

    @GetMapping("listele")
    public ModelAndView allPosts(ModelAndView modelAndView, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            modelAndView.addObject("newPostActive", "active");
            int userId = (int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            List<Post> posts;
            if(groupId == 1)
                posts = postRepository.findAllPostsByPrivacyListDescOrder(Arrays.asList(true, false));
            else
                posts = postRepository.findPostsByPostGroups(userRepository.findPostGroupsByUserId(userId));

            modelAndView.addObject("edit_or_delete_post_permission", permissionRepository.hasPermission(Perm.EDIT_OR_DELETE_POST, groupId));
            modelAndView.addObject("approve_post_permission", permissionRepository.hasPermission(Perm.APPROVE_POST, groupId));
            modelAndView.addObject("postActive", "active");

            modelAndView.addObject("posts", posts);
            modelAndView.setViewName("allPosts.jsp");
        }else{
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }


    @PostMapping("delete/{postId}")
    public ModelAndView deletePost(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                boolean bool = false;
                int userId = (int) session.getAttribute("id");
                for(PostGroup postGroup: userRepository.findPostGroupsByUserId(userId)){
                    if(postGroup.getId() == post.get().getPostGroup().getId()){
                        bool = true;
                        break;
                    }
                }
                int groupId = (int) session.getAttribute("groupId");
                if(bool && permissionRepository.hasPermission(Perm.EDIT_OR_DELETE_POST, groupId)){
                    postRepository.delete(post.get());
                    modelAndView.setViewName("redirect:/haber/listele");
                }else{
                    modelAndView.setViewName("redirect:/haber/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/haber/listele");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }

    @GetMapping("/onizle/{postId}")
    public ModelAndView previewPost(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId, RedirectAttributes redirectAttributes){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.APPROVE_POST, groupId)){
                Optional<Post> post = postRepository.findById(postId);
                if(post.isPresent()) {
                    redirectAttributes.addFlashAttribute("post", post.get());
                    redirectAttributes.addFlashAttribute("approve_post_permission", true);
                    modelAndView.setViewName("redirect:/haber/" + postId);
                }else{
                    modelAndView.setViewName("redirect:/haber/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/haber/listele");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }


        return modelAndView;
    }


    @PostMapping("/approve/{postId}")
    public ModelAndView approvePost(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.APPROVE_POST, groupId)){
                Optional<Post> post = postRepository.findById(postId);;
                if(post.isPresent()){
                    post.get().setVerified(true);
                    postRepository.saveAndFlush(post.get());
                    modelAndView.setViewName("redirect:/haber/listele");
                }else{
                    modelAndView.setViewName("redirect:/haber/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;

    }

    @PostMapping("/disapprove/{postId}")
    public ModelAndView disapprovePost(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.APPROVE_POST, groupId)){
                Optional<Post> post = postRepository.findById(postId);
                if(post.isPresent()){
                    post.get().setVerified(false);
                    postRepository.saveAndFlush(post.get());
                    modelAndView.setViewName("redirect:/haber/listele");
                }else{
                    modelAndView.setViewName("redirect:/haber/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;
    }


    @GetMapping("duzenle/{postId}")
    public ModelAndView previewPage(ModelAndView modelAndView, HttpSession session, @PathVariable("postId")final int postId, RedirectAttributes redirectAttributes) {

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn){
            int groupId = (int) session.getAttribute("groupId");
            if(permissionRepository.hasPermission(Perm.CREATE_POST, groupId)){
                Optional<Post> post = postRepository.findById(postId);
                if(post.isPresent()){
                    redirectAttributes.addFlashAttribute("updatePost", true);
                    redirectAttributes.addFlashAttribute("post", post.get());
                    modelAndView.setViewName("redirect:/haber/yeni");
                }else{
                    modelAndView.setViewName("redirect:/haber/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/haber/listele");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }
        return modelAndView;

    }


    @PostMapping("update/{postId}")
    public ModelAndView updatePost(ModelAndView modelAndView, HttpSession session, HttpServletRequest request, @PathVariable("postId")final int postId){

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if(isLoggedIn != null && isLoggedIn) {
            int userId = (int) session.getAttribute("id");
            int groupId = (int) session.getAttribute("groupId");
            if (permissionRepository.hasPermission(Perm.CREATE_POST, groupId)) {
                Optional<Post> post = postRepository.findById(postId);
                if(post.isPresent()){
                    String title = request.getParameter("title");
                    String postGroup = request.getParameter("post-group");
                    String content = request.getParameter("content");
                    String imgUrl = request.getParameter("img-url");
                    String privacy = request.getParameter("privacy");
                    boolean privacyBool = true;
                    if(privacy != null && privacy.equals("on"))
                        privacyBool = false;
                    postRepository.saveAndFlush(post.get().setImageUrl(imgUrl).setTitle(title).setContent(content).setPrivacy(privacyBool)
                    .setPostGroup(new PostGroup().setId(Integer.parseInt(postGroup))));
                    modelAndView.setViewName("redirect:/haber/"+postId);
                }else{
                    modelAndView.setViewName("redirect:/haberler/listele");
                }
            }else{
                modelAndView.setViewName("redirect:/haber/listele");
            }
        }else{
            modelAndView.setViewName("redirect:/giris");
        }

        return modelAndView;
    }
}
