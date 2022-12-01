package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
