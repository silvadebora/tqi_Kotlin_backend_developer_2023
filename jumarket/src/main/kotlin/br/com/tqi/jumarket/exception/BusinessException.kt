package br.com.tqi.jumarket.exception

data class BusinessException(override val message: String?): RuntimeException(message)
