package transactionImplementation

import payrollDatabase.PayrollDatabase
import payrollDomain.Affiliation
import payrollDomain.Employee
import payrollDomainImplementation.affiliation.NoAffiliation
import transactionAbstraction.ChangeAffiliationTransaction

class ChangeUnaffiliatedTransaction(empId: Int) : ChangeAffiliationTransaction(empId) {
    override fun getAffiliation(): Affiliation = NoAffiliation()

    override fun updateMembership(memberIdBef: Int?, e: Employee) {
        if (memberIdBef != null) {
            PayrollDatabase.removeUnionMember(memberIdBef)
        }
    }
}
