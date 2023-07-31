package br.com.tqi.jumarket.model.dto

import br.com.tqi.jumarket.model.ShoppingCart
import br.com.tqi.jumarket.model.ShoppingCartItem
import br.com.tqi.jumarket.model.view.ProductCheckoutView
import jakarta.validation.constraints.NotNull

data class ShoppingCartItemDTO(
    @field:NotNull(message = "Mandatory field") val product: ProductCheckoutView,
    @field:NotNull(message = "Mandatory field") val quantity: Int
) {
    fun toEntity(shoppingCart: ShoppingCart): ShoppingCartItem = ShoppingCartItem(
        product = this.product.toEntity(),
        quantity = this.quantity,
        shoppingCart = shoppingCart
    )
}