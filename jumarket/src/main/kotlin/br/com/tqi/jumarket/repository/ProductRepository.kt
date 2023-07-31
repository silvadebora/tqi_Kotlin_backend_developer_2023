package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

    fun findAllByCategoryId(categoryType: Long): List<Product>

}
