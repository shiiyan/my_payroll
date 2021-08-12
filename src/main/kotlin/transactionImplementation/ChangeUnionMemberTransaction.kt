package transactionImplementation

import payrollDatabase.PayrollDatabase
import payrollDomain.Affiliation
import payrollDomain.Employee
import payrollDomainImplementation.affiliation.UnionAffiliation
import transactionAbstraction.ChangeAffiliationTransaction

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
