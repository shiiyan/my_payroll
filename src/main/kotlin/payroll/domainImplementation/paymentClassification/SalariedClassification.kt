package payroll.domainImplementation.paymentClassification

import payroll.domain.Paycheck
import payroll.domain.PaymentClassification

class SalariedClassification(
    val itsSalary: Double
) : PaymentClassification {
    override fun calculatePay(pc: Paycheck): Double = itsSalary
}
