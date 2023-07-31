package br.com.tqi.jumarket.model.dto

import br.com.tqi.jumarket.model.Category
import jakarta.validation.constraints.NotEmpty

data class CategoryDTO(
    @field:NotEmpty(message = "Mandatory field") val description: String
) {
    fun toEntity(): Category = Category(
        description = this.description
    )
}