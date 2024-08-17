package model.deposit

import model.result.CoreResult

interface IDeposit {
    val currentDeposit: DepositAmount
    fun add(value: DepositAmount): CoreResult<DepositAmount>
    fun sub(value: DepositAmount): CoreResult<DepositAmount>
}

class Deposit(private var _currentDeposit: DepositAmount) : IDeposit {
    override val currentDeposit: DepositAmount get() = _currentDeposit

    override fun add(value: DepositAmount): CoreResult<DepositAmount> =
        try {
            _currentDeposit += value
            CoreResult(_currentDeposit)
        } catch (e: IllegalArgumentException) {
            CoreResult(_currentDeposit, errorMessage = e.message ?: "")
        }

    override fun sub(value: DepositAmount): CoreResult<DepositAmount> =
        try {
            _currentDeposit -= value
            CoreResult(_currentDeposit)
        } catch (e: IllegalArgumentException) {
            CoreResult(_currentDeposit, errorMessage = e.message ?: "")
        }
}