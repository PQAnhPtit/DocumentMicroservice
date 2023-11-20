package com.example.CategoryService.repository;

import com.example.CategoryService.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends PagingAndSortingRepository<Category, Integer> {

}
