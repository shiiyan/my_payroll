package transactionAbstraction

import payrollDomain.Employee

class ChangeAddressTransaction(
    empId: Int,
    private val itsAddress: String
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        e.changeAddress(itsAddress)
    }
}
