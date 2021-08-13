package payroll.domainImplementation.paycheck

import payroll.domain.Paycheck
import java.util.Calendar

data class SimplePaycheck(
    private val itsPayPeriodStartDate: Calendar,
    private val itsPayPeriodEndDate: Calendar,
    private var itsGrossPay: Double = 0.0,
    private var itsDeductions: Double = 0.0,
    private var itsNetPay: Double = 0.0
) : Paycheck {
    override fun getPayPeriodStartDate(): Calendar = itsPayPeriodStartDate

    override fun getPayPeriodEndDate(): Calendar = itsPayPeriodEndDate

    override fun getGrossPay(): Double = itsGrossPay

    override fun changeGrossPay(grossPay: Double) {
        itsGrossPay = grossPay
    }

    override fun getDeductions(): Double = itsDeductions

    override fun changeDeductions(deductions: Double) {
        itsDeductions = deductions
    }

    override fun getNetPay(): Double = itsNetPay

    override fun changeNetPay(netPay: Double) {
        itsNetPay = netPay
    }

    override fun getField(string: String): String? =
        if (string == "Disposition") "Hold"
        else null
}
