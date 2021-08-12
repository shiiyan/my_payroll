package payroll.transactionImplementation

import payroll.database.PayrollDatabase
import payroll.domain.Affiliation
import payroll.domain.Employee
import payroll.domainImplementation.affiliation.UnionAffiliation
import payroll.transactionAbstraction.ChangeAffiliationTransaction

class ChangeUnionMemberTransaction(
    empId: Int,
    private val itsMemberId: Int,
    private val itsDues: Double
) : ChangeAffiliationTransaction(empId) {
    override fun getAffiliation(): Affiliation = UnionAffiliation(
        itsMemberId = itsMemberId,
        itsDues = itsDues
    )

    override fun updateMembership(memberIdBef: Int?, e: Employee) {
        if (memberIdBef != null) {
            PayrollDatabase.removeUnionMember(memberIdBef)
        }

        PayrollDatabase.addUnionMember(itsMemberId, e)
    }
}
