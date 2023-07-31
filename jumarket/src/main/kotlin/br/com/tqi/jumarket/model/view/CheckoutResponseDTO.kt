package br.com.tqi.jumarket.model.view

import br.com.tqi.jumarket.enumeration.PaymentMethod
import br.com.tqi.jumarket.enumeration.Status
import br.com.tqi.jumarket.model.ShoppingCart
import br.com.tqi.jumarket.model.Transaction
import java.math.BigDecimal

data class CheckoutResponseDTO(
    val id: Long,
    val shoppingCart: ShoppingCart,
    val status: Status? = null,
    val paymentMethod: PaymentMethod? = null,
    val amount: BigDecimal
) {
    constructor(transaction: Transaction): this(
        id = transaction.id,
        shoppingCart = transaction.shoppingCart,
        status = transaction.status,
        paymentMethod = transaction.paymentMethod,
        amount = transaction.amount
    )
}

