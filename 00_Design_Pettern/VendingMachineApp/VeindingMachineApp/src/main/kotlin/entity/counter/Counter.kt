package entity.counter

abstract class Counter<T>(
    val data: T,
    protected var _amount: Int
) {
    val amount: Int = _amount

    fun add(data: T) {
        if(!isSame(data)) return

        _amount ++
    }

    fun subtract(data: T) {
        if(!isSame(data)) return

        _amount --
    }

    abstract fun isSame(data: T): Boolean
}