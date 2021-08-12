package transactionAbstraction

import payrollDomain.Employee
import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule

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
