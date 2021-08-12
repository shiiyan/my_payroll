package payroll.domainImplementation.paymentSchedule

import payroll.domain.PaymentSchedule
import java.util.Calendar

class MonthlySchedule : PaymentSchedule {
    private fun is25thDayOfMonth(date: Calendar): Boolean {
        val cal = Calendar.getInstance()
        cal.time = date.time
        return cal.get(Calendar.DATE) == 25
    }

    override fun getPayPeriodStartDate(payDate: Calendar): Calendar {
        val payPeriodStartDate = Calendar.getInstance()
        payPeriodStartDate.time = payDate.time
        payPeriodStartDate.add(Calendar.MONTH, -1)
        payPeriodStartDate.set(Calendar.DATE, 1)
        return payPeriodStartDate
    }

    override fun getPayPeriodEndDate(payDate: Calendar): Calendar {
        val payPeriodEndDate = Calendar.getInstance()
        payPeriodEndDate.time = payDate.time
        payPeriodEndDate.add(Calendar.MONTH, -1)
        payPeriodEndDate.set(Calendar.DATE, payPeriodEndDate.getActualMaximum(Calendar.DATE))
        return payPeriodEndDate
    }

    override fun isPayDate(payDate: Calendar): Boolean {
        return is25thDayOfMonth(payDate)
    }
}
