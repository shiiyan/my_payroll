package payroll.factoryImplementation

import payroll.domain.Paycheck
import payroll.domainImplementation.paycheck.SimplePaycheck
import payroll.factory.Factory
import java.util.Calendar

class SimpleFactory : Factory {
    override fun makePaycheck(payPeriodStartDate: Calendar, payDate: Calendar): Paycheck =
        SimplePaycheck(
            itsPayPeriodStartDate = payPeriodStartDate,
            itsPayPeriodEndDate = payDate
        )
}
