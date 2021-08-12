package payroll.domain

import java.util.Calendar

interface Paycheck {
    fun getPayPeriodStartDate(): Calendar
    fun getPayPeriodEndDate(): Calendar
    fun getGrossPay(): Double
    fun changeGrossPay(grossPay: Double)
    fun getDeductions(): Double
    fun changeDeductions(deductions: Double)
    fun getNetPay(): Double
    fun changeNetPay(netPay: Double)
    fun getField(string: String): String?
}
