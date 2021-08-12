package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.CommissionedClassification
import payroll.domainImplementation.paymentSchedule.BiweeklySchedule
import payroll.transactionAbstraction.ChangeClassificationTransaction

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
