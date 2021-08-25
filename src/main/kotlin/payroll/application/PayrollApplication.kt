package payroll.application

import payroll.transaction.TransactionSource

class PayrollApplication(
    private val itsTransactionSource: TransactionSource
) {
    fun main() {
        val t = itsTransactionSource.getTransaction()
        t.validate()
        t.execute()
    }
}
