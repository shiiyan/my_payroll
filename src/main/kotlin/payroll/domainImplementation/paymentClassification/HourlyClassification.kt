package payroll.domainImplementation.paymentClassification

import payroll.domain.Paycheck
import payroll.domain.PaymentClassification
import java.util.Calendar

class HourlyClassification(
    val itsHourlyRate: Double,
    private val itsTimeCards: MutableMap<Calendar, TimeCard> = mutableMapOf()
) : PaymentClassification {
    fun addTimeCard(date: Calendar, hours: Double) {
        itsTimeCards[date] = TimeCard(itsDate = date, itsHours = hours)
    }

    fun getTimeCard(date: Calendar) = itsTimeCards[date]

    override fun calculatePay(pc: Paycheck): Double {
        var totalPay = 0.0
        for (tc in itsTimeCards.values) {
            if (tc.itsDate in (pc.getPayPeriodStartDate()..pc.getPayPeriodEndDate())) {
                totalPay += if (tc.itsHours > 8.0) {
                    itsHourlyRate * 8 + (tc.itsHours - 8) * itsHourlyRate * 1.5
                } else {
                    itsHourlyRate * tc.itsHours
                }
            }
        }

        return totalPay
    }
}
