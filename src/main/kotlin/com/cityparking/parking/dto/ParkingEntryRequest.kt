package com.cityparking.parking.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ParkingEntryRequest(
    @field:NotBlank(message = "La placa no puede estar vacía")
    @field:Pattern(
        regexp = "^[A-Z]{3}-\\d{4}$",
        message = "La placa debe tener el formato AAA-1234"
    )
    val plate: String,

    @field:NotBlank(message = "El nombre del propietario no puede estar vacío")
    val ownerName: String
)
