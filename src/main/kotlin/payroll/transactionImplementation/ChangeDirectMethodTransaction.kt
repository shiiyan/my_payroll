package payroll.transactionImplementation

import payroll.domain.PaymentMethod
import payroll.domainImplementation.paymentMethod.DirectMethod
import payroll.transactionAbstraction.ChangeMethodTransaction

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
