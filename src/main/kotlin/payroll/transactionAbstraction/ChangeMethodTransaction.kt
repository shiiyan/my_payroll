package payroll.transactionAbstraction

import payroll.domain.Employee
import payroll.domain.PaymentMethod

abstract class ChangeMethodTransaction(
    empId: Int
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        val pm = getPaymentMethod()
        e.changeMethod(pm)
    }

    abstract fun getPaymentMethod(): PaymentMethod
}
