package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.dummyProduct
import br.com.tqi.jumarket.dummyProduct2
import br.com.tqi.jumarket.model.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@DataJpaTest(properties = ["spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    lateinit var repository: ProductRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun `on save should persist product`() {
        //def
        val cat = categoryRepository.save(Category(description = "Desc"))
        val savedProduct = repository.save(dummyProduct.copy(category = cat))
        //ast
        assertNotNull(savedProduct)
        assertEquals(dummyProduct.name, savedProduct.name)
        assertTrue(savedProduct.id > 0)
    }

    @Test
    fun `should list all products`() {
        //def
        val category = categoryRepository.save(Category(description = "Drinks"))
        repository.save(dummyProduct.copy(category = category))
        repository.save(dummyProduct2.copy(category = category))
        //act
        val products = repository.findAll()
        //ast
        assertEquals(2, products.size)
    }

    @Test
    fun `should update product`() {
        //def
        val category = categoryRepository.save(Category(description = "Milk"))
        val savedProduct = repository.save(dummyProduct.copy(category = category))
        //act
        repository.save(savedProduct.copy(name = "Abobora"))
        val retrievedProduct = repository.findById(savedProduct.id)
        //ast
        retrievedProduct.ifPresent {
            assertEquals(it.name, "Abobora")
        }
    }

    @Test
    fun `should delete product`() {
        //def
        val category = categoryRepository.save(Category(description = "Foods"))
        val savedProduct = repository.save(dummyProduct.copy(name = "Milk", category = category))
        //act
        repository.deleteById(savedProduct.id)
        val foundProduct = repository.findById(savedProduct.id)
        //ast
        assertTrue(foundProduct.isEmpty)
    }
}