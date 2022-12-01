package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    @Query("SELECT userAccount.user.id FROM UserAccount AS userAccount where userAccount.username= :username AND userAccount.password= :password")
    Integer findUserIdByUsernameAndPassword(String username, String password);

    @Query("SELECT userAccount FROM UserAccount AS userAccount WHERE userAccount.user.id= :userId")
    Optional<UserAccount> findUserAccountByUserId(int userId);
}
