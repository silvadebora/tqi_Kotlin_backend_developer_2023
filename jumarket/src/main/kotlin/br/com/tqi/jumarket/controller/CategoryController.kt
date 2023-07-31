package br.com.tqi.jumarket.controller

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.model.dto.CategoryDTO
import br.com.tqi.jumarket.model.update.CategoryUpdateDTO
import br.com.tqi.jumarket.model.view.CategoryView
import br.com.tqi.jumarket.service.impl.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun saveCategory(@RequestBody @Valid categoryDTO: CategoryDTO): ResponseEntity<CategoryView> {
        val category = categoryService.save(categoryDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CategoryView(category))
    }

    @GetMapping
    fun listCategories(): ResponseEntity<List<CategoryView>> {
        val categoryList: List<CategoryView> = categoryService.findAllCategories().stream()
            .map { category: Category -> CategoryView(category) }
            .collect(
                Collectors.toList()
            )
        return ResponseEntity.status(HttpStatus.OK).body(categoryList)
    }

    @GetMapping("/{description}")
    fun findByDescription(@PathVariable description: String):
            ResponseEntity<CategoryView> {
        val category: Category = categoryService.findByDescription(description)
        return ResponseEntity.status(HttpStatus.OK).body(CategoryView(category))

    }

    @DeleteMapping("/{id}")
    fun deleteCategory(categoryId: Long) = categoryService.deleteCategory(categoryId)

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody @Valid categoryUpdateDTO: CategoryUpdateDTO
    ):
            ResponseEntity<CategoryView> {
        val category = categoryService.findCategoryById(id)
        val categoryToUpdate = categoryUpdateDTO.toEntity(category)
        val categoryUpdate: Category = categoryService.save(categoryToUpdate)

        return ResponseEntity.status(HttpStatus.OK).body(CategoryView(categoryUpdate))
    }

}