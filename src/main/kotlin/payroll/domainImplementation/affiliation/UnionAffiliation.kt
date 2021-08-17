package payroll.domainImplementation.affiliation

import payroll.domain.Affiliation
import payroll.domain.Paycheck
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
        var totalServiceCharge = 0.0
        for (sc in itsServiceCharges.values) {
            if (sc.itsDate in (pc.getPayPeriodStartDate()..pc.getPayPeriodEndDate())) {
                totalServiceCharge += sc.itsAmount
            }
        }

        val fridays = getNumberOfFridaysInPayPeriods(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())
        val totalDues = itsDues * fridays

        return totalServiceCharge + totalDues
    }

    private fun getNumberOfFridaysInPayPeriods(payPeriodStart: Calendar, payPeriodEnd: Calendar): Int {
        var fridays = 0
        val cal = Calendar.getInstance()
        cal.time = payPeriodStart.time
        while (cal <= payPeriodEnd) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                fridays++
            }

            cal.add(Calendar.DATE, 1)
        }

        return fridays
    }
}
