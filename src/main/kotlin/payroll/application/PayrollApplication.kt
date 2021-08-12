package payroll.application

import payroll.transaction.TransactionSource

class PayrollApplication(
    private val transactionSource: TransactionSource
) {
    fun main() {
        val t = transactionSource.getTransaction()
        t.validate()
        t.execute()
    }
}
