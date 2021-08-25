package payroll.transactionSource

import payroll.transaction.Transaction
import payroll.transaction.TransactionSource

class TextParserTransactionSource(
    private val itsCommandLine: List<String>
) : TransactionSource {
    override fun getTransaction(): Transaction {
        TODO("get transaction by transaction name and parameter from its command line")
    }
}
