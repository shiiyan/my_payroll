package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.HourlyClassification
import payrollDomainImplementation.paymentSchedule.WeeklySchedule
import transactionAbstraction.ChangeClassificationTransaction

class ChangeHourlyClassificationTransaction(
    empId: Int,
    private val itsHourlyRate: Double
) : ChangeClassificationTransaction(empId) {
    override fun getPaymentClassification(): PaymentClassification = HourlyClassification(itsHourlyRate)

    override fun getPaymentSchedule(): PaymentSchedule = WeeklySchedule()
}
