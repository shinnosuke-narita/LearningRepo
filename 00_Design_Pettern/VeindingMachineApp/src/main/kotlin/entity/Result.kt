package entity

data class Result<T: Any?>(
    val data: T,
    val errorMessage: String = ""
) {
    fun isError() = errorMessage.isNotEmpty()
    fun isNotError() = errorMessage.isEmpty()

    inline fun onError(handle: (error: String) -> Unit): Result<T> {
        if (isNotError()) return this

        handle(errorMessage)
        return this
    }

    inline fun onSuccess(handle: (data: T) -> Unit): Result<T> {
        if (isError()) return this

        handle(data)
        return this
    }
}