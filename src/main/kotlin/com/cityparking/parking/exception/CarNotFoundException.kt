package com.cityparking.parking.exception

class CarNotFoundException(plate: String) : RuntimeException("No se encontró un vehículo con placa $plate en el parqueadero")
