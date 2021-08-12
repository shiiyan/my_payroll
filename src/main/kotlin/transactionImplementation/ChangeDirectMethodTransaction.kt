package transactionImplementation

import payrollDomain.PaymentMethod
import payrollDomainImplementation.paymentMethod.DirectMethod
import transactionAbstraction.ChangeMethodTransaction

class ChangeDirectMethodTransaction(
    empId: Int,
    private val itsBank: String,
    private val itsAccount: String
) : ChangeMethodTransaction(empId) {
    override fun getPaymentMethod(): PaymentMethod = DirectMethod(
        itsBank = itsBank,
        itsAccount = itsAccount
    )
}
