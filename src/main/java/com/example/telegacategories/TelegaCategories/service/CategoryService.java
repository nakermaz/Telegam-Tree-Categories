package com.example.telegacategories.TelegaCategories.service;

import com.example.telegacategories.TelegaCategories.db.Category;
import com.example.telegacategories.TelegaCategories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return printTree(categories, "");
    }

    public String printTree(List<Category> categories, String prefix) {
        StringBuilder sb = new StringBuilder();
        for (Category category : categories) {
            buildTreeString(sb, category, prefix);
        }
        return sb.toString();
    }

    private void buildTreeString(StringBuilder sb, Category category, String prefix) {
        sb.append(prefix).append(category.getName()).append("\n");
        for (Category child : category.getCategoryList()) {
            buildTreeString(sb, child, prefix + "--");
        }
    }

}
