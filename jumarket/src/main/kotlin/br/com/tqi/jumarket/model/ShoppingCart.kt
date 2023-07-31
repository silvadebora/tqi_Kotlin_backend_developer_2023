package br.com.tqi.jumarket.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "shopping_cart")
data class ShoppingCart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = true)
    val cpf: String = "",
    @Column(nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now()
)