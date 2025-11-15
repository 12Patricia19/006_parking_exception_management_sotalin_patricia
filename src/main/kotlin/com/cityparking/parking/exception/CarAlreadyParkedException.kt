package com.cityparking.parking.exception

class CarAlreadyParkedException(plate: String) : RuntimeException("El vehículo con placa $plate ya está registrado en el parqueadero")
