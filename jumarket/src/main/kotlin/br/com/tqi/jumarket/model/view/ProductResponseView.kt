package br.com.tqi.jumarket.model.view

import br.com.tqi.jumarket.model.Product
import java.math.BigDecimal

data class ProductResponseView(
    val id: Long,
    var name: String,
    var price: BigDecimal,
    var stockLevel: Int? = 0,
    var category: CategoryView,
    var measurement: String,
    var barCode: String
) {
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        price = product.price,
        stockLevel = product.stockLevel,
        category = CategoryView(product.category),
        measurement = product.measurement,
        barCode = product.barCode
    )
}