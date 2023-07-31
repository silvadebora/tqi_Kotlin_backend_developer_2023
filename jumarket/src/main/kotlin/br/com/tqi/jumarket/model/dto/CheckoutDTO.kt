package br.com.tqi.jumarket.model.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF

data class CheckoutDTO(
    @CPF(message = "Invalid CPF") val cpf: String,
    @field:NotNull(message = "Mandatory field") @field:Min(1) val items: List<ShoppingCartItemDTO>
)