package br.com.tqi.jumarket.model

import br.com.tqi.jumarket.enumeration.PaymentMethod
import br.com.tqi.jumarket.enumeration.Status
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    var shoppingCart: ShoppingCart,
    @Enumerated
    var status: Status = Status.OPEN,
    @Enumerated
    var paymentMethod: PaymentMethod? = null,
    @Column(nullable = false)
    var amount: BigDecimal
)