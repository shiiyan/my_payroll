package payroll.domainImplementation.paymentClassification

import payroll.domain.Paycheck
import payroll.domain.PaymentClassification
import java.util.Calendar

class CommissionedClassification(
    val itsSalary: Double,
    val itsCommissionRate: Double,
    private val itsReceipts: MutableMap<Calendar, SalesReceipt> = mutableMapOf()
) : PaymentClassification {
    fun addSalesReceipt(saleDate: Calendar, amount: Double) {
        itsReceipts[saleDate] = SalesReceipt(itsSaleDate = saleDate, itsAmount = amount)
    }

    fun getReceipt(date: Calendar) = itsReceipts[date]

    override fun calculatePay(pc: Paycheck): Double {
        var commission = 0.0
        for (receipt in itsReceipts.values) {
            if (receipt.itsSaleDate in (pc.getPayPeriodStartDate()..pc.getPayPeriodEndDate())) {
                commission += receipt.itsAmount * itsCommissionRate
            }
        }

        return itsSalary + commission
    }
}
