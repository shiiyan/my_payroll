package payrollDomainImplementation.paymentSchedule

import payrollDomain.PaymentSchedule
import java.util.Calendar

class WeeklySchedule : PaymentSchedule {
    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate: Calendar = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.add(Calendar.DATE, -6)
        return payPeriodStartDate
    }

    override fun isPayDate(payDate: Calendar): Boolean =
        payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}
