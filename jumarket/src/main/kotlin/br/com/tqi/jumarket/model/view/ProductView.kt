package br.com.tqi.jumarket.model.view

import br.com.tqi.jumarket.model.Product
import java.math.BigDecimal

data class ProductView(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val stockLevel: Int,
    val categoryId: Long,
    val measurement: String,
    var barCode: String
) {
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        price = product.price,
        stockLevel = product.stockLevel ?: 0,
        categoryId = product.category.id,
        measurement = product.measurement,
        barCode = product.barCode
    )
}