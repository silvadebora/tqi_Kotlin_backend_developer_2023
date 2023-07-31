package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Long>{

    fun findByDescriptionContains(description: String): Category

}