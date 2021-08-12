package payrollDomainImplementation.affiliation

import payrollDomain.Affiliation
import payrollDomain.Paycheck
import java.util.Calendar

class UnionAffiliation(
    val itsDues: Double,
    val itsMemberId: Int,
    private val itsServiceCharges: MutableMap<Calendar, ServiceCharge> = mutableMapOf()
) : Affiliation {
    override fun getMemberId() = itsMemberId

    override fun getServiceCharge(date: Calendar): Double? = itsServiceCharges[date]?.itsAmount

    fun addServiceCharge(date: Calendar, amount: Double) {
        itsServiceCharges[date] = ServiceCharge(itsDate = date, itsAmount = amount)
    }

    override fun calculateDeductions(pc: Paycheck): Double {
        TODO("Not yet implemented")
    }
}
