package payroll.domain

interface PaymentClassification {
    fun calculatePay(pc: Paycheck): Double
}
