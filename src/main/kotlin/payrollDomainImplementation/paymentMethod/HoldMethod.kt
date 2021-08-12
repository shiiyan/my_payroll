package payrollDomainImplementation.paymentMethod

import payrollDomain.Paycheck
import payrollDomain.PaymentMethod

class HoldMethod : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Hold payment.")
    }
}
