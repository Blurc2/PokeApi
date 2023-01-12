package com.gshp.desktop.domain.utils

sealed class Error(
    open val errorCode: Int = UNKNOWN_ERROR_CODE,
    open val message: String? = UNKNOWN_ERROR_MESSAGE,
    open val throwable: Throwable = Throwable(UNKNOWN_ERROR_MESSAGE)
) {

    data class Unknown(
        override val errorCode: Int = UNKNOWN_ERROR_CODE,
        override val message: String? = UNKNOWN_ERROR_MESSAGE,
        override val throwable: Throwable = Throwable(UNKNOWN_ERROR_MESSAGE)
    ) : Error()

    data class HTTPError(
        override val errorCode: Int = UNKNOWN_ERROR_CODE,
        override val message: String? = UNKNOWN_ERROR_MESSAGE,
        override val throwable: Throwable = Throwable(UNKNOWN_ERROR_MESSAGE)
    ) : Error()

    data class NetworkError(
        override val errorCode: Int = NETWORK_ERROR_CODE,
        override val message: String? = NETWORK_ERROR_MESSAGE,
        override val throwable: Throwable = Throwable(NETWORK_ERROR_MESSAGE)
    ) : Error()

    companion object {
        const val UNKNOWN_ERROR_MESSAGE = "Unknown Error"
        const val NETWORK_ERROR_MESSAGE = "Network Error"
        const val NETWORK_ERROR_CODE = -2
        const val UNKNOWN_ERROR_CODE = -1
    }
}
