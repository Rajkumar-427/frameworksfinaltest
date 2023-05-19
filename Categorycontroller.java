package com.raj.springBoot.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.springBoot.model.Category;
import com.raj.springBoot.repository.CategoryRepository;

@RestController
@RequestMapping("/api/Categories")
public class Categorycontroller {
	@Autowired
	private CategoryRepository categoryrepository{
		@GetMapping
		public Page<Category>getAllCategories(Pageable pageable){
			return categoryrepository.findAll(pageable);
		}
		@PostMapping
		public Category createCategory(@RequestBody Category category) {
			return categoryrepository.save(category);
		}
		@GetMapping("/{id}")
		public Category getCategoryById(@PathVariable("id") Long id) {
			return CategoryRepository.findById(id).orElseThrow(() -> 
			new NotFoundException("Category not found"));
		}
		@PutMapping("/{id}")
		public Category updateCategory(@PathVariable("id") Long id,@RequestBody Category updatedCategory) {
			Category category = CategoryRepository.findById(id).orElseThrow(() ->
			new NotFoundException("Category not found"));
			category.setName(updatedCategory.getName());
			category.CategoryRepository.save(category);
		}
	}

}
