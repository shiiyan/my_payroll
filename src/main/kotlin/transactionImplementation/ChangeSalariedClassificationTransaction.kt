package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.SalariedClassification
import payrollDomainImplementation.paymentSchedule.MonthlySchedule
import transactionAbstraction.ChangeClassificationTransaction

class ChangeSalariedClassificationTransaction(
    empId: Int,
    private val itsSalary: Double
) : ChangeClassificationTransaction(empId) {
    override fun getPaymentClassification(): PaymentClassification = SalariedClassification(itsSalary)

    override fun getPaymentSchedule(): PaymentSchedule = MonthlySchedule()
}
