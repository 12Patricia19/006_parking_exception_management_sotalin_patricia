package com.cityparking.parking.dto

import java.time.LocalDateTime

data class ParkingEntryResponse(
    val id: Long?,
    val plate: String,
    val ownerName: String,
    val entryTime: LocalDateTime
)
