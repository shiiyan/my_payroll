package payroll.transactionAbstraction

import payroll.database.PayrollDatabase
import payroll.domain.Employee
import payroll.domain.PaymentClassification
import payroll.domain.PaymentSchedule
import payroll.domainImplementation.paymentMethod.HoldMethod
import payroll.transaction.Transaction

abstract class AddEmployeeTransaction(
    private val itsEmpId: Int,
    private val itsName: String,
    private val itsAddress: String
) : Transaction {
    override fun validate() {
        require(itsName.isNotEmpty())
        require(itsAddress.isNotEmpty())
    }

    override fun execute() {
        val pc = getClassification()
        val ps = getSchedule()
        val pm = HoldMethod()

        val e = Employee.create(itsEmpId, itsName, itsAddress)
        e.changeClassification(pc)
        e.changeSchedule(ps)
        e.changeMethod(pm)
        PayrollDatabase.addEmployee(itsEmpId, e)
    }

    abstract fun getClassification(): PaymentClassification

    abstract fun getSchedule(): PaymentSchedule
}
