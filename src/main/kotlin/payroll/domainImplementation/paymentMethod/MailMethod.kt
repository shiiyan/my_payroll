package payroll.domainImplementation.paymentMethod

import payroll.domain.Paycheck
import payroll.domain.PaymentMethod

class MailMethod(
    val itsAddress: String
) : PaymentMethod {
    override fun pay(pc: Paycheck) {
        // TODO: Implement actual payment method.
        println("Mailed payment to $itsAddress")
    }
}
