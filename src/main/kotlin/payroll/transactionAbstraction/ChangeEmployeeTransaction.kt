package payroll.transactionAbstraction

import payroll.database.PayrollDatabase
import payroll.domain.Employee
import payroll.transaction.Transaction

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
