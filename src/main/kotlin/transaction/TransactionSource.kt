package transaction

interface TransactionSource {
    fun getTransaction(): Transaction
}
