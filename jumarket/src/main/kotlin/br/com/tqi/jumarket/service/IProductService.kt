package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.Product

interface IProductService {

    fun saveProduct(product: Product): Product

    fun findProductById(id: Long): Product

    fun findAllByCategory(categoryId: Long): List<Product>

    fun deleteProduct(productId: Long)
}