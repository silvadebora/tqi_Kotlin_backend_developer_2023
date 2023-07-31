package br.com.tqi.jumarket.service

import br.com.tqi.jumarket.model.Category
import br.com.tqi.jumarket.repository.CategoryRepository
import br.com.tqi.jumarket.service.impl.CategoryService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
@DataJpaTest(properties = ["spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"])
class CategoryServiceTest {

    @MockK
    lateinit var categoryRepository: CategoryRepository

    @InjectMockKs
    lateinit var categoryService: CategoryService

    @Test
    fun `should create category`() {
        //def
        val fakeCategory: Category = buildCategory()
        every { categoryRepository.save(any()) } returns fakeCategory
        //act
        val actual: Category = categoryService.save(fakeCategory)
        //ast
        assertNotNull(actual)
        assertEquals(actual, fakeCategory)
        verify(exactly = 1) { categoryRepository.save(fakeCategory) }
    }

    @Test
    fun `should find category by id`() {
        //def
        val fakeId: Long = Random().nextLong()
        val fakeCategory: Category = buildCategory(id = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        //act
        val actual = categoryService.findCategoryById(fakeId)
        // ast
        assertNotNull(actual)
        assertEquals(actual, fakeCategory)
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
    }

    @Test
    fun `should delete category by id`() {
        //def
        val fakeId: Long = Random().nextLong()
        val fakeCategory: Category = buildCategory(id = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        every { categoryRepository.delete(fakeCategory) } just runs

        //act
        categoryService.deleteCategory(fakeId)

        //ast
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
        verify(exactly = 1) { categoryRepository.delete(fakeCategory) }
    }


    companion object {
        fun buildCategory(
            id: Long = 1L,
            description: String = "Carnes"
        ) = Category(
            id = id,
            description = description
        )
    }
}