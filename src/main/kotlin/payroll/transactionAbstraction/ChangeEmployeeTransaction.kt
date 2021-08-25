package payroll.transactionAbstraction

import payroll.database.GlobalDatabase
import payroll.domain.Employee
import payroll.transaction.Transaction

abstract class ChangeEmployeeTransaction(
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val e = GlobalDatabase.payrollDatabase.getEmployee(itsEmpId) ?: throw RuntimeException("No such employee.")

        change(e)
    }

    abstract fun change(e: Employee)
}
