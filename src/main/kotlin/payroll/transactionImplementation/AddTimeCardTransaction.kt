package payroll.transactionImplementation

import payroll.database.GlobalDatabase
import payroll.domainImplementation.paymentClassification.HourlyClassification
import payroll.transaction.Transaction
import java.util.Calendar

class AddTimeCardTransaction(
    private val itsDate: Calendar,
    private val itsHours: Double,
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val e = GlobalDatabase.payrollDatabase.getEmployee(empId = itsEmpId) ?: throw RuntimeException("No such employee.")

        val hc = e.itsClassification as? HourlyClassification
            ?: throw RuntimeException("Tried to add timecard to non-hourly employee.")

        hc.addTimeCard(date = itsDate, hours = itsHours)
    }
}
