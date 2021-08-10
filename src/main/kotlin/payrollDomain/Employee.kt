package payrollDomain

import payrollImplementation.affiliation.NoAffiliation
import payrollImplementation.paymentClassification.SalariedClassification
import payrollImplementation.paymentMethod.DirectMethod
import payrollImplementation.paymentSchedule.MonthlySchedule

class Employee(
    val itsEmpId: Int,
    val itsName: String,
    val itsAddress: String,
    val itsClassification: PaymentClassification,
    val itsPaymentMethod: PaymentMethod,
    val itsSchedule: PaymentSchedule,
    val itsAffiliation: Affiliation
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
            itsClassification = SalariedClassification(),
            itsPaymentMethod = DirectMethod(),
            itsSchedule = MonthlySchedule(),
            itsAffiliation = NoAffiliation()
        )
    }
}
