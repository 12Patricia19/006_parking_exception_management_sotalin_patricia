package com.cityparking.parking.controller

import com.cityparking.parking.dto.ParkingEntryRequest
import com.cityparking.parking.dto.ParkingEntryResponse
import com.cityparking.parking.service.ParkingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/parking/entries")
class ParkingController(private val service: ParkingService) {

    @PostMapping
    fun registerEntry(@RequestBody request: ParkingEntryRequest): ResponseEntity<ParkingEntryResponse> {
        val entry = service.registerEntry(request.plate, request.ownerName)
        val response = ParkingEntryResponse(
            id = entry.id,
            plate = entry.plate,
            ownerName = entry.ownerName,
            entryTime = entry.entryTime
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{plate}")
    fun getEntry(@PathVariable plate: String): ResponseEntity<ParkingEntryResponse> {
        val entry = service.getEntryByPlate(plate)
        val response = ParkingEntryResponse(
            id = entry.id,
            plate = entry.plate,
            ownerName = entry.ownerName,
            entryTime = entry.entryTime
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{plate}")
    fun registerExit(@PathVariable plate: String): ResponseEntity<Void> {
        service.registerExit(plate)
        return ResponseEntity.noContent().build()
    }
}
