package payrollDomainImplementation.affiliation

import payrollDomain.Affiliation
import payrollDomain.Paycheck
import java.util.Calendar

class NoAffiliation : Affiliation {
    override fun getMemberId(): Int? = null

    override fun getServiceCharge(date: Calendar): Double = 0.00

    override fun calculateDeductions(pc: Paycheck): Double = 0.00
}
