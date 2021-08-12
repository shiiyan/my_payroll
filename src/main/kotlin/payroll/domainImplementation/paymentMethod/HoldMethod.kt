package payroll.domainImplementation.paymentMethod

import payroll.domain.Paycheck
import payroll.domain.PaymentMethod

class HoldMethod : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Hold payment for $pc")
    }
}
