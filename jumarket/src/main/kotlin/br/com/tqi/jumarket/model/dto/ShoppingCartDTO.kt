package br.com.tqi.jumarket.model.dto

import br.com.tqi.jumarket.model.ShoppingCart
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDateTime

data class ShoppingCartDTO(
    @CPF(message = "Invalid CPF") val cpf: String,
    val timestamp: LocalDateTime
) {
    fun toEntity(): ShoppingCart = ShoppingCart(
        cpf = this.cpf,
        timestamp = this.timestamp
    )
}
