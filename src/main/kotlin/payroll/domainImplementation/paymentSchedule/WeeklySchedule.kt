package payroll.domainImplementation.paymentSchedule

import payroll.domain.PaymentSchedule
import java.util.Calendar

class WeeklySchedule : PaymentSchedule {
    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate: Calendar = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.add(Calendar.DATE, -12)
        return payPeriodStartDate
    }

    override fun getPayPeriodEndDate(payDate: Calendar): Calendar {
        val payPeriodEndDate: Calendar = Calendar.getInstance()
        payPeriodEndDate.time = payDate.time
        payPeriodEndDate.add(Calendar.DATE, -6)
        return payPeriodEndDate
    }

    override fun isPayDate(payDate: Calendar): Boolean =
        payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}
