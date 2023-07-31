package br.com.tqi.jumarket.service.impl

import br.com.tqi.jumarket.model.Product
import br.com.tqi.jumarket.repository.ProductRepository
import br.com.tqi.jumarket.service.IProductService
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService
) : IProductService {

    override fun saveProduct(product: Product): Product {
        product.apply {
            category = categoryService.findCategoryById(product.category.id)
        }
        return this.productRepository.save(product)
    }

    override fun findProductById(id: Long): Product = productRepository.findById(id).orElseThrow()

    override fun findAllByCategory(categoryId: Long): List<Product> {
        return if (categoryId > 0) {
            productRepository.findAllByCategoryId(categoryId)
        } else {
            productRepository.findAll()
        }
    }


    override fun deleteProduct(productId: Long) {
        val product: Product = findProductById(productId)
        productRepository.delete(product)
    }

}

