package br.com.tqi.jumarket.service.impl

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.repository.CategoryRepository
import br.com.tqi.jumarket.service.ICategoryService
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) : ICategoryService {


    override fun save(category: Category): Category = categoryRepository.save(category)

    override fun findAllCategories(): MutableList<Category> = categoryRepository.findAll()

    override fun findCategoryById(categoryId: Long): Category =
        categoryRepository.findById(categoryId).orElseThrow()

    override fun findByDescription(description: String): Category =
        categoryRepository.findByDescriptionContains(description)

    override fun deleteCategory(categoryId: Long) {
        val category: Category = findCategoryById(categoryId)
        categoryRepository.delete(category)

    }


}