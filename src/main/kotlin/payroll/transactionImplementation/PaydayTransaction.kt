package payroll.transactionImplementation

import payroll.database.PayrollDatabase
import payroll.domain.Paycheck
import payroll.factory.Factory
import payroll.transaction.Transaction
import java.util.Calendar

class PaydayTransaction(
    private val itsPaydate: Calendar,
    private val itsPaycheck: MutableMap<Int, Paycheck> = mutableMapOf(),
    private val itsFactory: Factory
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val empIds = PayrollDatabase.getAllEmployeeIds()
        empIds.forEach { empId ->
            val e = PayrollDatabase.getEmployee(empId) ?: throw RuntimeException("No such employee.")
            if (e.isPayDate(itsPaydate)) {
                val pc = itsFactory.makePaycheck(e.getPayPeriodStartDate(itsPaydate), itsPaydate)
                itsPaycheck[empId] = pc
                e.payday(pc)
            }
        }
    }

    fun getPaycheck(empId: Int) = itsPaycheck[empId]
}
