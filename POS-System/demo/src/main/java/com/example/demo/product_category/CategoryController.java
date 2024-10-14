package com.example.demo.product_category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/pos/product/category")
public class CategoryController {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> getCategories() {
    return categoryService.getCategories();
  }

  @PostMapping
  public void addCategory(@RequestBody Category category) {
    categoryService.addCategory(category);
  }

  @DeleteMapping(path = "{categoryId}")
  public void deleteCategory(@PathVariable("categoryId") long categoryId) {
    categoryService.deleteCategory(categoryId);
  }


}
