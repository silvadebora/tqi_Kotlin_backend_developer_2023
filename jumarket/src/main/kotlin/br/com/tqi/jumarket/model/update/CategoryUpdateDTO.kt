package br.com.tqi.jumarket.model.update

import br.com.tqi.jumarket.model.Category
import jakarta.validation.constraints.NotEmpty

data class CategoryUpdateDTO(
    @field:NotEmpty(message = "Mandatory field") val description: String
) {
    fun toEntity(category: Category):Category{
        category.description = this.description
        return category
    }
}