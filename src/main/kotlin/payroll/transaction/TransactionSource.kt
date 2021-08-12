package payroll.transaction

interface TransactionSource {
    fun getTransaction(): Transaction
}
