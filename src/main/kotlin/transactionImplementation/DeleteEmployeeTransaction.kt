package transactionImplementation

import payrollDatabase.PayrollDatabase
import transaction.Transaction

class DeleteEmployeeTransaction(
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        PayrollDatabase.deleteEmployee(empId = itsEmpId)
    }
}
