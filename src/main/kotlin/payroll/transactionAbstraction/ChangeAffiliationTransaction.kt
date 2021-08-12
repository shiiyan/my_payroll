package payroll.transactionAbstraction

import payroll.domain.Affiliation
import payroll.domain.Employee

abstract class ChangeAffiliationTransaction(empId: Int) : ChangeEmployeeTransaction(empId) {
    override fun change(e: Employee) {
        val memberIdBef = e.itsAffiliation.getMemberId()

        val af = getAffiliation()
        e.changeAffiliation(af)

        updateMembership(memberIdBef = memberIdBef, e = e)
    }

    abstract fun getAffiliation(): Affiliation

    abstract fun updateMembership(memberIdBef: Int?, e: Employee)
}
