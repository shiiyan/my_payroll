package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.SalariedClassification
import payrollDomainImplementation.paymentSchedule.MonthlySchedule
import transactionAbstraction.AddEmployeeTransaction

class AddSalariedEmployeeTransaction(
    empId: Int,
    name: String,
    address: String,
    private val itsSalary: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = SalariedClassification(itsSalary)

    override fun getSchedule(): PaymentSchedule = MonthlySchedule()
}
