package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.dto.CheckoutDTO
import br.com.tqi.jumarket.model.ShoppingCartItem
import br.com.tqi.jumarket.model.dto.CheckoutFinishDTO
import br.com.tqi.jumarket.model.view.CheckoutResponseDTO
import jakarta.transaction.Transactional
import java.math.BigDecimal

interface ICheckoutService {

    @Transactional
    fun performCheckout(checkoutDTO: CheckoutDTO): CheckoutResponseDTO?

    fun cancellCheckout(id: Long): Boolean

    fun finishCheckout(id: Long, checkoutFinishDTO: CheckoutFinishDTO): Boolean

    fun calculeteTotalValue(items: List<ShoppingCartItem>): BigDecimal
}