package br.com.tqi.jumarket.model.update

import br.com.tqi.jumarket.model.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductUpdateDTO(
    @field:NotEmpty(message = "Ivalid input") val name: String,
    @field:NotNull(message = "Mandatory field") val price: BigDecimal,
    @field:NotNull(message = "Mandatory field") val stockLevel: Int,
    @field:NotNull(message = "Mandatory field") val categoryId: Long,
    @field:NotEmpty(message = "Ivalid input") val measurement: String
) {
    fun toEntity(product: Product): Product{
        product.name = this.name
        product.price = this.price
        product.stockLevel = this.stockLevel
        product.category.id = this.categoryId
        product.measurement = this.measurement
        return product
    }
}