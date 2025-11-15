package com.cityparking.parking.exception

class InvalidPlateFormatException(plate: String) : RuntimeException("La placa $plate no cumple con el formato requerido AAA-1234")
