package br.com.tqi.jumarket.model

import jakarta.persistence.*

@Entity(name = "shopping_cart_item")
data class ShoppingCartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    val product: Product,
    @Column(nullable = false)
    val quantity: Int = 0,
    @ManyToOne
    val shoppingCart: ShoppingCart
)