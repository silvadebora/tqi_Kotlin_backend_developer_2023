package br.com.tqi.jumarket.model.view

import br.com.tqi.jumarket.model.Category

data class CategoryView(
    val id: Long,
    val description: String
) {
    constructor(category: Category) : this(
        id = category.id,
        description = category.description
    )
}