package payroll.domainImplementation.paymentMethod

import payroll.domain.Paycheck
import payroll.domain.PaymentMethod

class DirectMethod(
    val itsBank: String,
    val itsAccount: String
) : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Directly paid to $itsAccount of $itsBank.")
    }
}
