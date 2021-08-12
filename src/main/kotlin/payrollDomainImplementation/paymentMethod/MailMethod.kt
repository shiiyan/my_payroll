package payrollDomainImplementation.paymentMethod

import payrollDomain.Paycheck
import payrollDomain.PaymentMethod

class MailMethod(
    val itsAddress: String
) : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Mailed payment to $itsAddress")
    }
}
