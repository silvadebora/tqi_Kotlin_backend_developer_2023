package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.model.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartRepository: JpaRepository<ShoppingCart, Long>