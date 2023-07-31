package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.dummyCategory
import br.com.tqi.jumarket.dummyCategory2
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
class CategoryRepositoryTest {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun `on save should persist category`() {
        //def
        val savedCategory = categoryRepository.save(dummyCategory)
        //ast
        assertNotNull(savedCategory)
        assertEquals(dummyCategory.description, savedCategory.description)
        assertTrue(savedCategory.id > 0)
    }

    @Test
    fun `should update category`() {
        //def
        val savedCategory = categoryRepository.save(dummyCategory2)
        //act
        categoryRepository.save(savedCategory.copy(description = "Drink"))
        val retrievedCategory = categoryRepository.findById(savedCategory.id)
        //ast
        retrievedCategory.ifPresent {
            assertEquals(it.description, "Drink")
        }
    }

    @Test
    fun `should delete category`() {
        //def
        val savedCategory = categoryRepository.save(Category(description = "Milk"))
        //act
        categoryRepository.deleteById(savedCategory.id)
        val foundCategory = categoryRepository.findById(savedCategory.id)
        //ast
        assertTrue(foundCategory.isEmpty)
    }

    @Test
    fun `should list all categories`() {
        //def
        categoryRepository.save(dummyCategory)
        categoryRepository.save(dummyCategory2)
        //act
        val categories = categoryRepository.findAll()
        //ast
        assertEquals(2, categories.size)
    }


}