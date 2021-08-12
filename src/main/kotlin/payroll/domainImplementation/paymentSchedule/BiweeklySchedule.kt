package payroll.domainImplementation.paymentSchedule

import payroll.domain.PaymentSchedule
import java.util.Calendar

class BiweeklySchedule : PaymentSchedule {
    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate: Calendar = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.add(Calendar.DATE, -13)
        return payPeriodStartDate
    }

    override fun isPayDate(payDate: Calendar): Boolean =
        payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}
