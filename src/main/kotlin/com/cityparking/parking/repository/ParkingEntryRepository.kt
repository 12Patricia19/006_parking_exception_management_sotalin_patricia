package com.cityparking.parking.repository

import com.cityparking.parking.entity.ParkingEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParkingEntryRepository : JpaRepository<ParkingEntry, Long> {
    fun findByPlate(plate: String): Optional<ParkingEntry>
    fun existsByPlate(plate: String): Boolean
}
