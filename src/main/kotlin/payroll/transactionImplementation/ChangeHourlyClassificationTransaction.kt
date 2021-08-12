package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.HourlyClassification
import payroll.domainImplementation.paymentSchedule.WeeklySchedule
import payroll.transactionAbstraction.ChangeClassificationTransaction

class ChangeHourlyClassificationTransaction(
    empId: Int,
    private val itsHourlyRate: Double
) : ChangeClassificationTransaction(empId) {
    override fun getPaymentClassification(): PaymentClassification = HourlyClassification(itsHourlyRate)

    override fun getPaymentSchedule(): PaymentSchedule = WeeklySchedule()
}
