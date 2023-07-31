package br.com.tqi.jumarket.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    var name: String = "",
    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO,
    var stockLevel: Int? = 0,
    @JoinColumn(nullable = false)
    @ManyToOne
    var category: Category = Category(),
    @Column(nullable = false)
    var measurement: String = "",
    @Column(nullable = false)
    var barCode: String = "",
    @Column
    var image: String = ""
    )