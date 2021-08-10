package payrollDomain

import java.util.Calendar

interface Affiliation {
    fun getServiceCharge(date: Calendar): Double
    fun calculateDeductions(pc: Paycheck): Double
}
