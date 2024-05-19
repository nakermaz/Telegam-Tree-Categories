package com.example.telegacategories.TelegaCategories.service;

import com.example.telegacategories.TelegaCategories.db.Category;
import com.example.telegacategories.TelegaCategories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public String addElement(String element){
        String [] parAndChild = element.split(" ");
        if (parAndChild.length == 1){
            Category category = new Category();

            category.setName(parAndChild[0]);
            categoryRepository.save(category);
            return "Корневая категория " + parAndChild[0] + " успешно создана.";
        } else if (parAndChild.length == 2) {
            Optional<Category> optionalCategory = categoryRepository.findByName(parAndChild[0]);

            if (optionalCategory.isPresent()){
                Category parentCategory = optionalCategory.get();
                Category childCategory = new Category();

                childCategory.setName(parAndChild[1]);
                childCategory.setParent(parentCategory);

                List<Category> listChild = parentCategory.getCategoryList();
                listChild.add(childCategory);
                parentCategory.setCategoryList(listChild);

                categoryRepository.save(childCategory);
                categoryRepository.save(parentCategory);
                return "Родитель " + parAndChild[0] + " успешно сохранил дочернюю категорию " + parAndChild[1];
            }

            return "Родителя " + parAndChild[0] + " не существует, пожалуйста создай его.";
        } else {
            return "Ошибка ввода элемента.";
        }
    }

    public String deleteTreeElements(String nameTree){
        Optional<Category> optionalCategory = categoryRepository.findByName(nameTree);
        if (optionalCategory.isPresent()){
            System.out.println("пришла инфа");
            Category category = optionalCategory.get();
            categoryRepository.delete(category);
            return "Дерево " + nameTree + " успешно удалена.";
        }
        return "Ошибка в названии.";
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
