package transactionImplementation

import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentClassification.HourlyClassification
import payrollDomainImplementation.paymentSchedule.WeeklySchedule
import transactionAbstraction.AddEmployeeTransaction

class AddHourlyEmployeeTransaction(
    empId: Int,
    name: String,
    address: String,
    private val itsHourlyRate: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = HourlyClassification(itsHourlyRate)

    override fun getSchedule(): PaymentSchedule = WeeklySchedule()
}
