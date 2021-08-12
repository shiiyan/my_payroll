package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.CommissionedClassification
import payrollDomainImplementation.paymentSchedule.BiweeklySchedule
import transactionAbstraction.AddEmployeeTransaction

class AddCommissionedEmployeeTransaction(
    empId: Int,
    name: String,
    address: String,
    private val itsSalary: Double,
    private val itsCommissionRate: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = CommissionedClassification(itsSalary, itsCommissionRate)

    override fun getSchedule(): PaymentSchedule = BiweeklySchedule()
}
