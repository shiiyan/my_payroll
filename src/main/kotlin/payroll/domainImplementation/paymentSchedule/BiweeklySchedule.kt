package payroll.domainImplementation.paymentSchedule

import payroll.domain.PaymentSchedule
import java.util.Calendar
import java.util.GregorianCalendar

class BiweeklySchedule : PaymentSchedule {
    private val FIRST_PAYABLE_FRIDAY = GregorianCalendar(2021, Calendar.JULY, 9)

    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate: Calendar = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.add(Calendar.DATE, -13)
        return payPeriodStartDate
    }

    override fun getPayPeriodEndDate(payDate: Calendar): Calendar = payDate

    override fun isPayDate(payDate: Calendar): Boolean {
        if (payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            val cal = Calendar.getInstance()
            cal.time = FIRST_PAYABLE_FRIDAY.time
            while (cal <= payDate) {
                if (cal == payDate) {
                    return true
                }

                cal.add(Calendar.DATE, 14)
            }
        }
        return false
    }
}
