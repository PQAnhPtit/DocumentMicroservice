package com.example.UserService.repository;

import com.example.UserService.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends PagingAndSortingRepository<User, Integer> {

}
