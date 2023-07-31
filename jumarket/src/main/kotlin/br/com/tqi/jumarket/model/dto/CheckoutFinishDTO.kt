package br.com.tqi.jumarket.model.dto

import br.com.tqi.jumarket.enumeration.PaymentMethod

data class CheckoutFinishDTO(
    val paymentMethod: PaymentMethod
)