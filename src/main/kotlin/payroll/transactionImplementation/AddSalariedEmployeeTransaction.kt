package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.SalariedClassification
import payroll.domainImplementation.paymentSchedule.MonthlySchedule
import payroll.transactionAbstraction.AddEmployeeTransaction

class AddSalariedEmployeeTransaction(
    empId: Int,
    name: String,
    address: String,
    private val itsSalary: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = SalariedClassification(itsSalary)

    override fun getSchedule(): PaymentSchedule = MonthlySchedule()
}
