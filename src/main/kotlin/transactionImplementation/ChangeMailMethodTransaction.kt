package transactionImplementation

import payrollDomain.PaymentMethod
import payrollDomainImplementation.paymentMethod.MailMethod
import transactionAbstraction.ChangeMethodTransaction

class ChangeMailMethodTransaction(
    empId: Int,
    private val itsAddress: String,
) : ChangeMethodTransaction(empId) {
    override fun getPaymentMethod(): PaymentMethod = MailMethod(itsAddress)
}
