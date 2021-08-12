package payroll.transactionImplementation

import payroll.domain.Employee
import payroll.transactionAbstraction.ChangeEmployeeTransaction

class ChangeNameTransaction(
    empId: Int,
    private val itsName: String
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        e.changeName(itsName)
    }
}
