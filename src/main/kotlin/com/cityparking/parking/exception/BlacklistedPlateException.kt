package com.cityparking.parking.exception

class BlacklistedPlateException(plate: String) : RuntimeException("La placa $plate está en la lista negra y no puede usar el parqueadero")
