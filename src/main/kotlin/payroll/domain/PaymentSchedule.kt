package payroll.domain

import java.util.Calendar

interface PaymentSchedule {
    fun isPayDate(payDate: Calendar): Boolean

    fun getPayPeriodStartDate(payDate: Calendar): Calendar

    fun getPayPeriodEndDate(payDate: Calendar): Calendar
}
