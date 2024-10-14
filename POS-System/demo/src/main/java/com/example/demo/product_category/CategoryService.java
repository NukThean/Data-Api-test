package com.example.demo.product_category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.product.ProductRepository;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository,
      ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  public void addCategory(Category category) {
    categoryRepository.save(category);
  }

  public void deleteCategory(Long id) {
    boolean exists = categoryRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Category with id " + id + " does not exists");
    }
    int associatedProductsCount = productRepository.countByCategoryId(id);
    if (associatedProductsCount > 0) {
      throw new IllegalStateException("Category cannot be deleted because it has "
          + associatedProductsCount + " associated products");
    }
    categoryRepository.deleteById(id);
  }
}
