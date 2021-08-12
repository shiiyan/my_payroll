package payroll.transactionImplementation

import payroll.domain.PaymentMethod
import payroll.domainImplementation.paymentMethod.HoldMethod
import payroll.transactionAbstraction.ChangeMethodTransaction

class ChangeHoldMethodTransaction(empId: Int) : ChangeMethodTransaction(empId) {
    override fun getPaymentMethod(): PaymentMethod = HoldMethod()
}
