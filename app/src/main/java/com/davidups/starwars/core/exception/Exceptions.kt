package com.davidups.starwars.core.exception

import com.davidups.starwars.core.exception.ErrorHandler

class NoResponseException(message: String? = ErrorHandler.UNKNOWN_ERROR) : Exception(message)

class NoDataException(message: String? = ErrorHandler.NO_SUCH_DATA) : Exception()

data class ErrorResponse(val cod: Int, val message: String)
