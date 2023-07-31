package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.model.Product
import br.com.tqi.jumarket.model.dto.CheckoutDTO
import br.com.tqi.jumarket.model.dto.ShoppingCartItemDTO
import br.com.tqi.jumarket.model.view.ProductCheckoutView
import br.com.tqi.jumarket.repository.*
import br.com.tqi.jumarket.service.impl.CheckoutService
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(properties = ["spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"])
class CheckoutServiceTest {


    lateinit var checkoutService: CheckoutService

    @Autowired
    lateinit var shoppingCartItemRepository: ShoppingCartItemRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var shoppingCartRepository: ShoppingCartRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var productRepository: ProductRepository


    @BeforeEach
    fun setup() {
        checkoutService = CheckoutService(
            shoppingCartRepository,
            shoppingCartItemRepository,
            transactionRepository,
            productRepository
        )
        categoryRepository.save(buildCategory())
        productRepository.save(buildProduct())
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create a shopping cart save a transaction`() {

        // def
        val checkoutDTO = buildCheckoutDTO(
            cpf = "02033366625",
            items = listOf(
                buildShoppingCartItemDTO(buildProductCheckoutView(1), quantity = 5),
            )
        )

        // act
        val response = checkoutService.performCheckout(checkoutDTO)

        // ast
        assertEquals(1, response?.id)
        assertTrue(response?.amount?.compareTo(BigDecimal(250)) == 0)
    }


    companion object {
        fun buildCheckoutDTO(
            cpf: String,
            items: List<ShoppingCartItemDTO> = emptyList()
        ): CheckoutDTO = CheckoutDTO(
            cpf = cpf,
            items = items
        )


        fun buildShoppingCartItemDTO(
            product: ProductCheckoutView = buildProductCheckoutView(1),
            quantity: Int = 3
        ): ShoppingCartItemDTO = ShoppingCartItemDTO(
            product = product,
            quantity = quantity
        )

        fun buildProductCheckoutView(
            id: Long = 1L
        ): ProductCheckoutView = ProductCheckoutView(
            id = id
        )

        private fun buildProduct(
            id: Long = 1L,
            name: String = "Picanha",
            price: BigDecimal = BigDecimal.valueOf(50.0),
            stockLevel: Int = 30,
            category: Category = CategoryServiceTest.buildCategory()
        ): Product = Product(
            id = id,
            name = name,
            price = price,
            stockLevel = stockLevel,
            category = category
        )

        fun buildCategory(
            id: Long = 1L,
            description: String = "Carnes"
        ) = Category(
            id = id,
            description = description
        )
    }


}