package payrollImplementation.affiliation

import payrollDomain.Affiliation
import payrollDomain.Paycheck
import java.util.Calendar

class UnionAffiliation(
    val itsDues: Double,
    val itsMemberId: Int,
    private val itsServiceCharges: MutableMap<Calendar, ServiceCharge>
) : Affiliation {
    override fun getServiceCharge(date: Calendar): Double? = itsServiceCharges[date]?.itsAmount

    override fun calculateDeductions(pc: Paycheck): Double {
        TODO("Not yet implemented")
    }
}
