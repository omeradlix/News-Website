package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Post;
import com.newsportal.newsportal.model.PostGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value="SELECT * FROM post WHERE verified= true ORDER BY created_at DESC LIMIT 5", nativeQuery=true)
    List<Post> findLast5PostsOrderByDescCreated_At();

    @Query(value="SELECT * FROM post WHERE verified= true AND privacy= :privacy ORDER BY created_at DESC LIMIT 5", nativeQuery=true)
    List<Post> findLast5PostsOrderByPrivacyDescCreated_At(boolean privacy);

    @Query("SELECT post FROM Post AS post WHERE post.postGroup IN :postGroups")
    List<Post> findPostsByPostGroups(List<PostGroup> postGroups);

    @Query("SELECT post FROM Post AS post WHERE post.privacy IN :privacyList ORDER BY post.created_at DESC")
    List<Post> findAllPostsByPrivacyListDescOrder(List<Boolean> privacyList);

    @Query("SELECT post FROM Post AS post WHERE post.postGroup.id IN :postGroups AND post.privacy IN :privacyList ORDER BY post.created_at DESC")
    List<Post> findAllPostsByPostGroupsAndPrivacyDescOrder(List<Integer> postGroups, List<Boolean> privacyList);

    @Query("SELECT post FROM Post AS post WHERE post.postGroup.id IN :postGroups AND post.privacy IN :privacyList ORDER BY post.created_at ASC")
    List<Post> findAllPostsByPostGroupsAndPrivacyAscOrder(List<Integer> postGroups, List<Boolean> privacyList);
}
