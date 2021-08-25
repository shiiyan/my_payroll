package payroll.transactionImplementation

import payroll.database.GlobalDatabase
import payroll.domainImplementation.affiliation.UnionAffiliation
import payroll.transaction.Transaction
import java.util.Calendar

class AddServiceChargeTransaction(
    private val itsMemberId: Int,
    private val itsDate: Calendar,
    private val itsAmount: Double
) : Transaction {
    override fun validate() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val e = GlobalDatabase.payrollDatabase.getUnionMember(memberId = itsMemberId)
            ?: throw RuntimeException("No such employee.")

        val ua = e.itsAffiliation as? UnionAffiliation
            ?: throw RuntimeException("Tried to add service charge to non-union affiliation.")

        ua.addServiceCharge(date = itsDate, amount = itsAmount)
    }
}
