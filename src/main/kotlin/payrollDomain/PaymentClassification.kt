package payrollDomain

interface PaymentClassification {
    fun calculatePay(pc: Paycheck): Double
}
