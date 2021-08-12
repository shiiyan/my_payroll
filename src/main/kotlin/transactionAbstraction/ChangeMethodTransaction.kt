package transactionAbstraction

import payrollDomain.Employee
import payrollDomain.PaymentMethod

abstract class ChangeMethodTransaction(
    empId: Int
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        val pm = getPaymentMethod()
        e.changeMethod(pm)
    }

    abstract fun getPaymentMethod(): PaymentMethod
}
