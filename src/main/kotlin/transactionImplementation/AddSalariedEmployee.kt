package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollImplementation.paymentClassification.SalariedClassification
import payrollImplementation.paymentSchedule.MonthlySchedule
import transactionAbstraction.AddEmployeeTransaction

class AddSalariedEmployee(
    empId: Int,
    name: String,
    address: String,
    private val itsSalary: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = SalariedClassification(itsSalary)

    override fun getSchedule(): PaymentSchedule = MonthlySchedule()
}
