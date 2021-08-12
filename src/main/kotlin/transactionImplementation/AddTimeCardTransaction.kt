package transactionImplementation

import payrollDatabase.PayrollDatabase
import payrollDomainImplementation.paymentClassification.HourlyClassification
import transaction.Transaction
import java.util.Calendar

class AddTimeCardTransaction(
    private val itsDate: Calendar,
    private val itsHours: Double,
    private val itsEmpId: Int
) : Transaction {
    override fun execute() {
        val e = PayrollDatabase.getEmployee(empId = itsEmpId) ?: throw RuntimeException("No such employee.")

        val hc = e.itsClassification as? HourlyClassification
            ?: throw RuntimeException("Tried to add timecard to non-hourly employee.")

        hc.addTimeCard(date = itsDate, hours = itsHours)
    }
}
