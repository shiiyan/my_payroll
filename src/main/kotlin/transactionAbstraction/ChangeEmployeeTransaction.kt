package transactionAbstraction

import payrollDatabase.PayrollDatabase
import payrollDomain.Employee
import transaction.Transaction

abstract class ChangeEmployeeTransaction(
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val e = PayrollDatabase.getEmployee(itsEmpId) ?: throw RuntimeException("No such employee.")

        change(e)
    }

    abstract fun change(e: Employee)
}
