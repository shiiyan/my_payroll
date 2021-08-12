package transaction

interface Transaction {
    fun validate()
    fun execute()
}
