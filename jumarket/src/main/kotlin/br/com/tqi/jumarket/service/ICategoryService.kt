package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.Category

interface ICategoryService {

    fun save(category: Category): Category

    fun findCategoryById(categoryId: Long): Category

    fun findAllCategories(): MutableList<Category>

    fun findByDescription(description: String): Category

    fun deleteCategory(categoryId: Long)

}