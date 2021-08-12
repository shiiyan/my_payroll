package transactionAbstraction

import payrollDatabase.PayrollDatabase
import payrollDomain.Employee
import transaction.Transaction

abstract class ChangeEmployeeTransaction(
    private val itsEmpId: Int
) : Transaction {
    override fun execute() {
        val e = PayrollDatabase.getEmployee(itsEmpId)
        if (e != null) {
            change(e)
        }
    }

    abstract fun change(e: Employee)
}
