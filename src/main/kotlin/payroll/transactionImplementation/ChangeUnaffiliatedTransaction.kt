package payroll.transactionImplementation

import payroll.database.GlobalDatabase
import payroll.domain.Affiliation
import payroll.domain.Employee
import payroll.domainImplementation.affiliation.NoAffiliation
import payroll.transactionAbstraction.ChangeAffiliationTransaction

class ChangeUnaffiliatedTransaction(empId: Int) : ChangeAffiliationTransaction(empId) {
    override fun getAffiliation(): Affiliation = NoAffiliation()

    override fun updateMembership(memberIdBef: Int?, e: Employee) {
        if (memberIdBef != null) {
            GlobalDatabase.payrollDatabase.removeUnionMember(memberIdBef)
        }
    }
}
