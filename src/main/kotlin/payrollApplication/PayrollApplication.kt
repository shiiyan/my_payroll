package payrollApplication

import transaction.TransactionSource

class PayrollApplication(
    private val transactionSource: TransactionSource
) {
    fun main() {
        val t = transactionSource.getTransaction()
        t.validate()
        t.execute()
    }
}
