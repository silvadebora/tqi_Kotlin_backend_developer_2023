package br.com.tqi.jumarket.model.view

import br.com.tqi.jumarket.model.Product

data class ProductCheckoutView(
    val id: Long
) {
    fun toEntity(): Product = Product(
        id = this.id
    )

}