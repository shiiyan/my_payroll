package payrollDomainImplementation.paymentMethod

import payrollDomain.Paycheck
import payrollDomain.PaymentMethod

class DirectMethod(
    val itsBank: String,
    val itsAccount: String
) : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Directly paid to $itsAccount of $itsBank.")
    }
}
