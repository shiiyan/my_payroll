package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.SalariedClassification
import payroll.domainImplementation.paymentSchedule.MonthlySchedule
import payroll.transactionAbstraction.ChangeClassificationTransaction

class ChangeSalariedClassificationTransaction(
    empId: Int,
    private val itsSalary: Double
) : ChangeClassificationTransaction(empId) {
    override fun getPaymentClassification(): PaymentClassification = SalariedClassification(itsSalary)

    override fun getPaymentSchedule(): PaymentSchedule = MonthlySchedule()
}
