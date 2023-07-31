package br.com.tqi.jumarket.controller

import br.com.tqi.jumarket.model.Product
import br.com.tqi.jumarket.model.dto.ProductDTO
import br.com.tqi.jumarket.model.update.ProductUpdateDTO
import br.com.tqi.jumarket.model.view.ProductResponseView
import br.com.tqi.jumarket.model.view.ProductView
import br.com.tqi.jumarket.service.impl.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun saveProduct(@RequestBody @Valid productDTO: ProductDTO): ResponseEntity<ProductView> {
        val product = productService.saveProduct(productDTO.toEntity())
        return ResponseEntity.status(HttpStatus.OK).body(ProductView(product))
    }

    @GetMapping
    fun findAllByCategoryId(@RequestParam(value = "categoryId") categoryId: Long):
            ResponseEntity<List<ProductView>> {
        val productList: List<ProductView> =
            productService.findAllByCategory(categoryId).stream()
                .map { product: Product -> ProductView(product) }
                .collect(
                    Collectors.toList()
                )
        return ResponseEntity.status(HttpStatus.OK).body(productList)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ProductResponseView> {
        val product: Product = productService.findProductById(id)
        return ResponseEntity.status(HttpStatus.OK).body(ProductResponseView(product))
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(productId: Long) = productService.deleteProduct(productId)

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody @Valid productUpdateDTO: ProductUpdateDTO
    ): ResponseEntity<ProductView> {
        val product = productService.findProductById(id)
        val productToUpdate = productUpdateDTO.toEntity(product)
        val productUpdate: Product = productService.saveProduct(productToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(ProductView(productUpdate))
    }

}