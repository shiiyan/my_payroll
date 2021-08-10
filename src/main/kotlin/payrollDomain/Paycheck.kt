package payrollDomain

import java.util.Calendar

interface Paycheck {
    val payPeriodStartDate: Calendar
    val payPeriodEndDate: Calendar

    fun setGrossPay(grossPay: Double)
    fun setDeductions(deductions: Double)
    fun setNetPay(netPay: Double)
}
