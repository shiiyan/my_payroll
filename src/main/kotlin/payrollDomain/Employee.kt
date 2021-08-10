package payrollDomain

import payrollImplementation.affiliation.NoAffiliation
import payrollImplementation.paymentClassification.SalariedClassification
import payrollImplementation.paymentMethod.DirectMethod
import payrollImplementation.paymentSchedule.MonthlySchedule
import java.util.Calendar

class Employee(
    val itsEmpId: Int,
    var itsName: String,
    var itsAddress: String,
    var itsClassification: PaymentClassification,
    var itsPaymentMethod: PaymentMethod,
    var itsSchedule: PaymentSchedule,
    var itsAffiliation: Affiliation
) {
    companion object {
        fun create(
            empId: Int,
            name: String,
            address: String
        ) = Employee(
            itsEmpId = empId,
            itsName = name,
            itsAddress = address,
            itsClassification = SalariedClassification(0.0),
            itsPaymentMethod = DirectMethod(),
            itsSchedule = MonthlySchedule(),
            itsAffiliation = NoAffiliation()
        )
    }

    fun changeName(name: String) {
        itsName = name
    }

    fun changeAddress(address: String) {
        itsAddress = address
    }

    fun changeClassification(pc: PaymentClassification) {
        itsClassification = pc
    }

    fun changeMethod(pm: PaymentMethod) {
        itsPaymentMethod = pm
    }

    fun changeSchedule(ps: PaymentSchedule) {
        itsSchedule = ps
    }

    fun changeAffiliation(af: Affiliation) {
        itsAffiliation = af
    }

    fun isPayDate(payDate: Calendar): Boolean = itsSchedule.isPayDate(payDate)

    fun getPayPeriodStartDate(payDate: Calendar): Calendar = itsSchedule.getPayPeriodStartDate(payDate)

    fun payday(pc: Paycheck) {
        val grossPay: Double = itsClassification.calculatePay(pc)
        val deductions: Double = itsAffiliation.calculateDeductions(pc)
        val netPay = grossPay - deductions

        pc.setGrossPay(grossPay)
        pc.setDeductions(deductions)
        pc.setNetPay(netPay)
        itsPaymentMethod.pay(pc)
    }
}
