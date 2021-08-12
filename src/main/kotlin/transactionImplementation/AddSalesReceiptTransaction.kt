package transactionImplementation

import payrollDatabase.PayrollDatabase
import payrollDomainImplementation.paymentClassification.CommissionedClassification
import transaction.Transaction
import java.util.Calendar

class AddSalesReceiptTransaction(
    private val itsSaleDate: Calendar,
    private val itsAmount: Double,
    private val itsEmpId: Int
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val e = PayrollDatabase.getEmployee(empId = itsEmpId) ?: throw RuntimeException("No such employee.")

        val cc = e.itsClassification as? CommissionedClassification
            ?: throw RuntimeException("Tried to add timecard to non-hourly employee.")

        cc.addSalesReceipt(saleDate = itsSaleDate, amount = itsAmount)
    }
}
