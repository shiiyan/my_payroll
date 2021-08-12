package payroll.domainImplementation.paymentSchedule

import payroll.domain.PaymentSchedule
import java.util.Calendar

class MonthlySchedule : PaymentSchedule {
    private fun isLastDayOfMonth(date: Calendar): Boolean {
        val cal = Calendar.getInstance()
        cal.time = date.time
        return cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE)
    }

    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.set(Calendar.DATE, 1)
        return payPeriodStartDate
    }

    override fun isPayDate(payDate: Calendar): Boolean {
        return isLastDayOfMonth(payDate)
    }
}
