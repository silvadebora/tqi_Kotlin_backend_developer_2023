package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.model.Product
import br.com.tqi.jumarket.repository.ProductRepository
import br.com.tqi.jumarket.service.impl.CategoryService
import br.com.tqi.jumarket.service.impl.ProductService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
@DataJpaTest(properties = ["spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"])
class ProductServiceTest {

    @MockK
    lateinit var categoryService: CategoryService

    @MockK
    lateinit var productRepository: ProductRepository

    @InjectMockKs
    lateinit var productService: ProductService

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        productService = ProductService(productRepository, categoryService)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create product`() {
        // def
        val product: Product = buildProduct()
        val categoryId = 1L

        every { categoryService.findCategoryById(categoryId) } returns product.category
        every { productRepository.save(product) } returns product
        // act
        val actual: Product = productService.saveProduct(product)

        // ast
        verify(exactly = 1) { categoryService.findCategoryById(categoryId) }
        verify(exactly = 1) { productRepository.save(product) }
        assertNotNull(actual)
        assertEquals(actual, product)
    }

    @Test
    fun `should return list of products for a category`() {
        // def
        val categoryrId = 1L
        val expectedProducts: List<Product> = listOf(buildProduct(), buildProduct(), buildProduct())
        // act
        every { productRepository.findAllByCategoryId(categoryrId) } returns expectedProducts
        val actual: List<Product> = productService.findAllByCategory(categoryrId)
        // ast
        assertNotNull(actual)
        assertEquals(actual, expectedProducts)
        verify(exactly = 1) { productRepository.findAllByCategoryId(categoryrId) }

    }

    @Test
    fun `should delete product by id`() {
        // def
        val fakeId: Long = Random().nextLong()
        val fakeProduct: Product = buildProduct(id = fakeId)
        every { productRepository.findById(fakeId) } returns Optional.of(fakeProduct)
        every { productRepository.delete(fakeProduct) } just runs

        // act
        productService.deleteProduct(fakeId)

        // ast
        verify(exactly = 1) { productRepository.findById(fakeId) }
        verify(exactly = 1) { productRepository.delete(fakeProduct) }

    }

    companion object {
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
    }
}