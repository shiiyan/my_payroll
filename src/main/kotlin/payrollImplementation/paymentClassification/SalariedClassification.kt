package payrollImplementation.paymentClassification

import payrollDomain.Paycheck
import payrollDomain.PaymentClassification

class SalariedClassification(
    val itsSalary: Double
) : PaymentClassification {
    override fun calculatePay(pc: Paycheck): Double = itsSalary
}
