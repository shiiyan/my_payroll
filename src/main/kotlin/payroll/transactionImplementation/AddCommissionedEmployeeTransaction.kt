package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.CommissionedClassification
import payroll.domainImplementation.paymentSchedule.BiweeklySchedule
import payroll.transactionAbstraction.AddEmployeeTransaction

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
