package transactionImplementation

import payrollDatabase.PayrollDatabase
import payrollImplementation.affiliation.UnionAffiliation
import transaction.Transaction
import java.util.Calendar

class AddServiceChargeTransaction(
    private val itsMemberId: Int,
    private val itsDate: Calendar,
    private val itsAmount: Double
) : Transaction {
    override fun execute() {
        val e = PayrollDatabase.getUnionMember(memberId = itsMemberId)
            ?: throw RuntimeException("No such employee.")

        val ua = e.itsAffiliation as? UnionAffiliation
            ?: throw RuntimeException("Tried to add service charge to non-union affiliation.")

        ua.addServiceCharge(date = itsDate, amount = itsAmount)
    }
}
