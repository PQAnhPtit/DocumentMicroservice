package com.example.AdminService.repository;

import com.example.AdminService.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends PagingAndSortingRepository<Admin, Integer> {

}
