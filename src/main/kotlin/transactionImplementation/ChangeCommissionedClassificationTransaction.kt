package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.CommissionedClassification
import payrollDomainImplementation.paymentSchedule.BiweeklySchedule
import transactionAbstraction.ChangeClassificationTransaction

class ChangeCommissionedClassificationTransaction(
    empId: Int,
    private val itsSalary: Double,
    private val itsCommissionRate: Double
) : ChangeClassificationTransaction(empId) {
    override fun getPaymentClassification(): PaymentClassification = CommissionedClassification(
        itsSalary = itsSalary, itsCommissionRate = itsCommissionRate
    )

    override fun getPaymentSchedule(): PaymentSchedule = BiweeklySchedule()
}
