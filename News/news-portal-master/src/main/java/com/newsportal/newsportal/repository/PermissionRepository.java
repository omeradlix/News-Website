package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("SELECT CASE WHEN COUNT(permission)>0 THEN TRUE ELSE FALSE END FROM Permission AS permission INNER JOIN permission.groups AS groups WHERE permission.id= ?1 AND groups.id= ?2")
    boolean hasPermission(int permissionId, int groupId);
}
