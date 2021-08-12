package transactionImplementation

import payrollDomain.PaymentMethod
import payrollDomainImplementation.paymentMethod.HoldMethod
import transactionAbstraction.ChangeMethodTransaction

class ChangeHoldMethodTransaction(empId: Int) : ChangeMethodTransaction(empId) {
    override fun getPaymentMethod(): PaymentMethod = HoldMethod()
}
