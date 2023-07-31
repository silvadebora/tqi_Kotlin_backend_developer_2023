package br.com.tqi.jumarket.repository

import br.com.tqi.jumarket.model.ShoppingCartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartItemRepository:JpaRepository<ShoppingCartItem, Long>