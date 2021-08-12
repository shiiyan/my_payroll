package payroll.transaction

interface Transaction {
    fun validate()
    fun execute()
}
