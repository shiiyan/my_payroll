package payroll.transactionImplementation

import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentClassification.HourlyClassification
import payroll.domainImplementation.paymentSchedule.WeeklySchedule
import payroll.transactionAbstraction.AddEmployeeTransaction

class AddHourlyEmployeeTransaction(
    empId: Int,
    name: String,
    address: String,
    private val itsHourlyRate: Double
) : AddEmployeeTransaction(empId, name, address) {
    override fun getClassification(): PaymentClassification = HourlyClassification(itsHourlyRate)

    override fun getSchedule(): PaymentSchedule = WeeklySchedule()
}
