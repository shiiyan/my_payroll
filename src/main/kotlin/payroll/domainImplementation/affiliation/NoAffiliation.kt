package payroll.domainImplementation.affiliation

import payroll.domain.Affiliation
import payroll.domain.Paycheck
import java.util.Calendar

class NoAffiliation : Affiliation {
    override fun getMemberId(): Int? = null

    override fun getServiceCharge(date: Calendar): Double = 0.0

    override fun calculateDeductions(pc: Paycheck): Double = 0.0
}
