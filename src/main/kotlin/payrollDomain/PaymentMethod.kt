package payrollDomain

interface PaymentMethod {
    fun pay(pc: Paycheck)
}
