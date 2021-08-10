package payrollImplementation.paymentClassification

import payrollDomain.Paycheck
import payrollDomain.PaymentClassification
import java.util.Calendar

class HourlyClassification(
    private val itsHourlyRate: Double,
    private val itsTimeCards: MutableMap<Calendar, TimeCard>
) : PaymentClassification {
    fun addTimeCard(date: Calendar, hours: Double) {
        itsTimeCards[date] = TimeCard(itsDate = date, itsHours = hours)
    }

    override fun calculatePay(pc: Paycheck): Double {
        var totalPay = 0.0
        for (tc in itsTimeCards.values) {
            if (tc.itsDate in (pc.payPeriodStartDate..pc.payPeriodEndDate)) {
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
