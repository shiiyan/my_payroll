package transactionAbstraction

import payrollDatabase.PayrollDatabase
import payrollDomain.Employee
import payrollDomain.PaymentClassification
import payrollDomain.PaymentSchedule
import payrollDomainImplementation.paymentMethod.HoldMethod
import transaction.Transaction

abstract class AddEmployeeTransaction(
    private val itsEmpId: Int,
    private val itsName: String,
    private val itsAddress: String
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
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
