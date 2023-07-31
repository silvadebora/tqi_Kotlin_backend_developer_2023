package br.com.tqi.jumarket.service.impl

import br.com.tqi.jumarket.enumeration.Status
import br.com.tqi.jumarket.exception.StockException
import br.com.tqi.jumarket.model.dto.CheckoutDTO
import br.com.tqi.jumarket.model.ShoppingCart
import br.com.tqi.jumarket.model.ShoppingCartItem
import br.com.tqi.jumarket.model.Transaction
import br.com.tqi.jumarket.model.dto.CheckoutFinishDTO
import br.com.tqi.jumarket.model.view.CheckoutResponseDTO
import br.com.tqi.jumarket.repository.ProductRepository
import br.com.tqi.jumarket.repository.ShoppingCartItemRepository
import br.com.tqi.jumarket.repository.ShoppingCartRepository
import br.com.tqi.jumarket.repository.TransactionRepository
import br.com.tqi.jumarket.service.ICheckoutService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CheckoutService(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val shoppingCartItemRepository: ShoppingCartItemRepository,
    private val transactionRepository: TransactionRepository,
    private val productRepository: ProductRepository
) : ICheckoutService {

    @Transactional
    override fun performCheckout(checkoutDTO: CheckoutDTO): CheckoutResponseDTO? {
        val shoppingCart = shoppingCartRepository.save(ShoppingCart(cpf = checkoutDTO.cpf))
        val items = mutableListOf<ShoppingCartItem>()
        if (shoppingCart.id > 0) {
            checkoutDTO.items.forEach { item ->
                val cartItem = item.toEntity(shoppingCart)
                val result = shoppingCartItemRepository.save(cartItem)
                val product = productRepository.findById(cartItem.product.id)
                product.ifPresent {
                    val stockLevel = it.stockLevel
                    if (stockLevel != null && stockLevel >= cartItem.quantity) {
                        it.stockLevel = stockLevel - cartItem.quantity
                        productRepository.save(it)
                    } else {
                        throw StockException()
                    }
                }
                items.add(result)
            }
            val amount = calculeteTotalValue(items)
            val transaction = Transaction(
                shoppingCart = shoppingCart,
                amount = amount
            )
            val savedTransaction = transactionRepository.save(transaction)
            return CheckoutResponseDTO(savedTransaction)
        }
        return null
    }

    override fun cancellCheckout(id: Long): Boolean {
        val transaction: Transaction = transactionRepository.findById(id).orElse(null)
        if(transaction.status == Status.OPEN){
            transaction.status = Status.CANCELLED
            transactionRepository.save(transaction)
            return true
        }
        return false
    }

    override fun finishCheckout(id: Long, checkoutFinishDTO: CheckoutFinishDTO): Boolean {
        val transaction: Transaction = transactionRepository.findById(id).orElse(null)
        if(transaction.status == Status.OPEN){
            transaction.status = Status.FINISHED
            transaction.paymentMethod = checkoutFinishDTO.paymentMethod
            transactionRepository.save(transaction)
            return true
        }
        return false
    }


    override fun calculeteTotalValue(items: List<ShoppingCartItem>): BigDecimal {
        var totalValue = BigDecimal.ZERO
        items.forEach {
            val item = productRepository.findById(it.product.id)
            item.ifPresent { product ->
                val value = product.price.multiply(it.quantity.toBigDecimal())
                totalValue =  totalValue.add(value)
            }
        }
        return totalValue
    }
}