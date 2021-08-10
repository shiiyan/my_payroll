package payrollImplementation.paymentClassification

import payrollDomain.Paycheck
import payrollDomain.PaymentClassification
import java.util.Calendar

class CommissionedClassification(
    val itsSalary: Double,
    val itsCommissionRate: Double,
    private val itsReceipts: MutableMap<Calendar, SalesReceipt>
) : PaymentClassification {
    fun addSaleReceipt(saleDate: Calendar, amount: Double) {
        itsReceipts[saleDate] = SalesReceipt(itsSaleDate = saleDate, itsAmount = amount)
    }

    fun getReceipt(date: Calendar) = itsReceipts[date]

    override fun calculatePay(pc: Paycheck): Double {
        var commission = 0.0
        for (receipt in itsReceipts.values) {
            if (receipt.itsSaleDate in (pc.payPeriodStartDate..pc.payPeriodEndDate)) {
                commission += receipt.itsAmount * itsCommissionRate
            }
        }

        return itsSalary + commission
    }
}
