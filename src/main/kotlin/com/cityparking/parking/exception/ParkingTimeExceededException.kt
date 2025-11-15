package com.cityparking.parking.exception

class ParkingTimeExceededException(plate: String) : RuntimeException("El vehículo con placa $plate ha excedido el tiempo máximo de permanencia de 8 horas")
