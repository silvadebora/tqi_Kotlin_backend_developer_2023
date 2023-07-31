package br.com.tqi.jumarket.controller

import br.com.tqi.jumarket.model.dto.CheckoutDTO
import br.com.tqi.jumarket.model.dto.CheckoutFinishDTO
import br.com.tqi.jumarket.model.view.CheckoutResponseDTO
import br.com.tqi.jumarket.service.impl.CheckoutService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/checkout")
class CheckoutController(
    val checkoutService: CheckoutService
) {

    @PostMapping
    fun performCheckout(@RequestBody checkoutDTO: CheckoutDTO): ResponseEntity<CheckoutResponseDTO>{
        val checkoutResponse = checkoutService.performCheckout(checkoutDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse)
    }

    @PatchMapping("cancel/{id}")
    fun cancelCheckout(@PathVariable id: Long): ResponseEntity<Unit>{
        val success = checkoutService.cancellCheckout(id)
        return ResponseEntity.status(if(success)HttpStatus.NO_CONTENT else HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    @PatchMapping("finish/{id}")
    fun finishCheckout(@PathVariable id: Long, @RequestBody checkoutFinishDTO: CheckoutFinishDTO): ResponseEntity<Unit>{
        val success = checkoutService.finishCheckout(id, checkoutFinishDTO)
        return ResponseEntity.status(if(success)HttpStatus.NO_CONTENT else HttpStatus.INTERNAL_SERVER_ERROR).build()
    }
}