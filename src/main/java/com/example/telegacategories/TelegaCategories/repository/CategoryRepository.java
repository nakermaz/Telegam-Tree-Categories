package com.example.telegacategories.TelegaCategories.repository;

import com.example.telegacategories.TelegaCategories.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);


}
