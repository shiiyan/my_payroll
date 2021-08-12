package payroll.domain

interface PaymentMethod {
    fun pay(pc: Paycheck)
}
