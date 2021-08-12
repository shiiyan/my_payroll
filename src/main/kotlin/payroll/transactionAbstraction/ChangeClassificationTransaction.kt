package payroll.transactionAbstraction

import payroll.domain.Employee
import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule

abstract class ChangeClassificationTransaction(
    empId: Int
) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        val pc = getPaymentClassification()
        val ps = getPaymentSchedule()

        e.changeClassification(pc)
        e.changeSchedule(ps)
    }

    abstract fun getPaymentClassification(): PaymentClassification

    abstract fun getPaymentSchedule(): PaymentSchedule
}
