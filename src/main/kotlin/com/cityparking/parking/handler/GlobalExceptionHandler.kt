package com.cityparking.parking.handler

import com.cityparking.parking.dto.ErrorResponse
import com.cityparking.parking.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ParkingFullException::class)
    fun handleParkingFullException(ex: ParkingFullException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Parqueadero lleno",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(CarAlreadyParkedException::class)
    fun handleCarAlreadyParkedException(ex: CarAlreadyParkedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Auto ya registrado",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(InvalidPlateFormatException::class)
    fun handleInvalidPlateFormatException(ex: InvalidPlateFormatException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Formato de placa inválido",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(BlacklistedPlateException::class)
    fun handleBlacklistedPlateException(ex: BlacklistedPlateException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Placa en lista negra",
            status = HttpStatus.FORBIDDEN.value()
        )
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse)
    }

    @ExceptionHandler(CarNotFoundException::class)
    fun handleCarNotFoundException(ex: CarNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Auto no encontrado",
            status = HttpStatus.NOT_FOUND.value()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(ParkingTimeExceededException::class)
    fun handleParkingTimeExceededException(ex: ParkingTimeExceededException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            error = ex.message ?: "Tiempo de permanencia excedido",
            status = HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
