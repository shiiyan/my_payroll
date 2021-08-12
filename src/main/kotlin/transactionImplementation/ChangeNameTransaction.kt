package transactionImplementation

import payrollDomain.Employee
import transactionAbstraction.ChangeEmployeeTransaction

class ChangeNameTransaction(
    empId: Int,
    private val itsName: String
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        e.changeName(itsName)
    }
}
