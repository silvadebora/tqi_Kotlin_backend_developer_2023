package br.com.tqi.jumarket.model.dto

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.model.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductDTO(
    @field:NotEmpty(message = "Mandatory field") val name: String,
    @field:NotNull(message = "Mandatory field") val price: BigDecimal,
    @field:NotNull(message = "Mandatory field") val stockLevel: Int,
    @field:NotNull(message = "Mandatory field") val categoryId: Long,
    @field:NotEmpty(message = "Mandatory field") val measurement: String,
    @field:NotEmpty(message = "Mandatory field") val barCode: String
) {
    fun toEntity(): Product = Product(
        name = this.name,
        price = this.price,
        stockLevel = this.stockLevel,
        category = Category(id = this.categoryId),
        measurement = this.measurement,
        barCode = this.barCode
    )
}