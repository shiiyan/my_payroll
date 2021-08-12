package payroll.transactionImplementation

import payroll.domain.PaymentMethod
import payroll.domainImplementation.paymentMethod.MailMethod
import payroll.transactionAbstraction.ChangeMethodTransaction

class ChangeMailMethodTransaction(
    empId: Int,
    private val itsAddress: String,
) : ChangeMethodTransaction(empId) {
    override fun getPaymentMethod(): PaymentMethod = MailMethod(itsAddress)
}
