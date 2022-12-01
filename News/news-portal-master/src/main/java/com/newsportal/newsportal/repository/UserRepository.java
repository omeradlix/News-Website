package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.PostGroup;
import com.newsportal.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT user.group.id FROM User AS user WHERE user.id= :userId")
    Integer findGroupIdByUserId(int userId);

    @Query("SELECT user.postGroup FROM User AS user WHERE user.id= :userId")
    List<PostGroup> findPostGroupsByUserId(int userId);

    @Query("SELECT user FROM User AS user WHERE user.group.id= :groupId")
    List<User> findUsersByGroupId(int groupId);


}
