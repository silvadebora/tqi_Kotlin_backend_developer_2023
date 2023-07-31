package br.com.tqi.jumarket.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(exception: MethodArgumentNotValidException):
            ResponseEntity<ExceptionDetails> {
        val error = exception.bindingResult.allErrors.associate {
            (it as FieldError).field to it.defaultMessage
        }
        return ResponseEntity(
            ExceptionDetails(
                title = "Bad Request!",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = exception.javaClass.toString(),
                details = error
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(exception: DataAccessException):
            ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                ExceptionDetails(
                    title = "Confict!",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = exception.javaClass.toString(),
                    details = mapOf(exception.cause.toString() to exception.message)
                )
            )
    }

    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(exception: BusinessException):
            ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExceptionDetails(
                    title = "Bad Response!",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = exception.javaClass.toString(),
                    details = mapOf(exception.cause.toString() to exception.message)
                )
            )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerValidException(exception: IllegalArgumentException):
            ResponseEntity<ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ExceptionDetails(
                    title = "Bad Request!",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = exception.javaClass.toString(),
                    details = mapOf(exception.cause.toString() to exception.message)
                )
            )
    }
}