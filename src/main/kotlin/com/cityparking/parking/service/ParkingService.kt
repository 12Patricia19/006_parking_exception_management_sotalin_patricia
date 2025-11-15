package com.cityparking.parking.service

import com.cityparking.parking.entity.ParkingEntry
import com.cityparking.parking.exception.*
import com.cityparking.parking.repository.ParkingEntryRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class ParkingService(private val repository: ParkingEntryRepository) {

    private val maxCapacity = 20
    private val maxHours = 8L
    private val blacklistedPlates = setOf("AAA-0001", "BBB-0002")
    private val plateRegex = Regex("^[A-Z]{3}-\\d{4}$")

    fun registerEntry(plate: String, ownerName: String): ParkingEntry {
        validatePlateFormat(plate)
        checkBlacklist(plate)
        checkCapacity()
        checkDuplicatePlate(plate)

        val entry = ParkingEntry(
            plate = plate,
            ownerName = ownerName,
            entryTime = LocalDateTime.now()
        )
        return repository.save(entry)
    }

    fun getEntryByPlate(plate: String): ParkingEntry {
        return repository.findByPlate(plate)
            .orElseThrow { CarNotFoundException(plate) }
    }

    fun registerExit(plate: String) {
        val entry = repository.findByPlate(plate)
            .orElseThrow { CarNotFoundException(plate) }

        val duration = Duration.between(entry.entryTime, LocalDateTime.now())
        if (duration.toHours() > maxHours) {
            throw ParkingTimeExceededException(plate)
        }

        repository.delete(entry)
    }

    private fun validatePlateFormat(plate: String) {
        if (!plateRegex.matches(plate)) {
            throw InvalidPlateFormatException(plate)
        }
    }

    private fun checkBlacklist(plate: String) {
        if (plate in blacklistedPlates) {
            throw BlacklistedPlateException(plate)
        }
    }

    private fun checkCapacity() {
        if (repository.count() >= maxCapacity) {
            throw ParkingFullException()
        }
    }

    private fun checkDuplicatePlate(plate: String) {
        if (repository.existsByPlate(plate)) {
            throw CarAlreadyParkedException(plate)
        }
    }
}
