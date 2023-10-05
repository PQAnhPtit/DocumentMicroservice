package com.example.RoleService.repository;

import com.example.RoleService.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends PagingAndSortingRepository<Role, Integer> {

}
