package payroll.transactionImplementation

import payroll.database.GlobalDatabase
import payroll.transaction.Transaction

class DeleteEmployeeTransaction(
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        GlobalDatabase.payrollDatabase.deleteEmployee(empId = itsEmpId)
    }
}
