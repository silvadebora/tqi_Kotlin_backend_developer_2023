package br.com.tqi.jumarket.exception

import java.time.LocalDateTime

data class ExceptionDetails(
    val title: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val exception: String,
    val details: Map<String, String?>
)
