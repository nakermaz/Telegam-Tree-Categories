package com.example.telegacategories.TelegaCategories;

import com.example.telegacategories.TelegaCategories.db.Category;
import com.example.telegacategories.TelegaCategories.repository.CategoryRepository;
import com.example.telegacategories.TelegaCategories.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    private Category fakeCategory;
    private List<Category> fakeListCategory;

    @BeforeEach
    void init(){
        fakeCategory = new Category("FakeCategory");
        fakeListCategory = List.of(fakeCategory);
    }

    @Test
    void getCategoriesTest(){
        when(categoryRepository.findAll()).thenReturn(fakeListCategory);
        String test = categoryService.getCategories();
        assertEquals(test, "FakeCategory\n");
    }

    @Test
    void addChildTestTrue(){
        when(categoryRepository.findByName("FakeCategory")).thenReturn(Optional.ofNullable(fakeCategory));
        String element = "FakeCategory NewChild";
        String answer = categoryService.addElement(element);
        assertEquals(answer, "Родитель " + fakeCategory.getName() + " успешно сохранил дочернюю категорию NewChild");
    }

    @Test
    void addChildTestFalse(){
        String element = "FakeCategory NewChild";
        String answer = categoryService.addElement(element);
        assertEquals(answer, "Родителя FakeCategory не существует, пожалуйста создай его.");
    }

    @Test
    void addChildErrorTest(){
        String element = "FakeCategory NewChild NewChild2";
        String answer = categoryService.addElement(element);
        assertEquals(answer, "Ошибка ввода элемента.");
    }

    @Test
    void deleteTreeElementsTest(){
        when(categoryRepository.findByName("FakeCategory")).thenReturn(Optional.ofNullable(fakeCategory));
        String elementName = "FakeCategory";
        String answer = categoryService.deleteTreeElements(elementName);
        assertEquals(answer, "Дерево " + elementName + " успешно удалена.");
    }

    @Test
    void deleteTreeElementsErrorTest(){
        String elementName = "FakeCategory";
        String answer = categoryService.deleteTreeElements(elementName);
        assertEquals(answer, "Ошибка в названии.");
    }
}
