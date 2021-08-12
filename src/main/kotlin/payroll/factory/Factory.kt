package payroll.factory

import payroll.domain.Paycheck
import java.util.Calendar

interface Factory {
    fun makePaycheck(payPeriodStartDate: Calendar, payPeriodEndDate: Calendar): Paycheck
}
