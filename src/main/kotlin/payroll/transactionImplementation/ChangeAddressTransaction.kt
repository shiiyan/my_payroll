package payroll.transactionImplementation

import payroll.domain.Employee
import payroll.transactionAbstraction.ChangeEmployeeTransaction

class ChangeAddressTransaction(
    empId: Int,
    private val itsAddress: String
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        e.changeAddress(itsAddress)
    }
}
