package br.com.tqi.jumarket

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.model.Product
import java.math.BigDecimal

val dummyCategory = Category(
    description = "Carnes"
)

val dummyCategory2 = Category(
    description = "Cereais"
)

val dummyProduct = Product(
    name = "Sucrilhos",
    price = BigDecimal.valueOf(11.90),
    stockLevel = 30,
    category = dummyCategory2
)

val dummyProduct2 = Product(
    name = "Picanha",
    price = BigDecimal.valueOf(50),
    stockLevel = 30,
    category = dummyCategory
)
