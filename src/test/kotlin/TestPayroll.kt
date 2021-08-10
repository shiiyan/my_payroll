import org.junit.jupiter.api.Test
import payrollDatabase.PayrollDatabase
import payrollImplementation.paymentClassification.CommissionedClassification
import payrollImplementation.paymentClassification.HourlyClassification
import payrollImplementation.paymentClassification.SalariedClassification
import payrollImplementation.paymentMethod.HoldMethod
import payrollImplementation.paymentSchedule.BiweeklySchedule
import payrollImplementation.paymentSchedule.MonthlySchedule
import payrollImplementation.paymentSchedule.WeeklySchedule
import transactionImplementation.AddCommissionedEmployee
import transactionImplementation.AddHourlyEmployee
import transactionImplementation.AddSalariedEmployee
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TestPayroll {
    @Test
    fun testAddSalariedEmployee() {
        println("TestAddSalariedEmployee")
        val empId = 1
        val t = AddSalariedEmployee(empId, "Bob", "Home", 1000.00)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Bob", e.itsName)
        val sc = e.itsClassification as SalariedClassification
        assertNotNull(sc)
        assertEquals(1000.00, sc.itsSalary)
        val ms = e.itsSchedule as MonthlySchedule
        assertNotNull(ms)
        val hm = e.itsPaymentMethod as HoldMethod
        assertNotNull(hm)
    }

    @Test
    fun testAddHourlyEmployee() {
        println("TestAddHourlyEmployee")
        val empId = 2
        val t = AddHourlyEmployee(empId, "Bill", "Home", 15.25)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Bill", e.itsName)
        val hc = e.itsClassification as HourlyClassification
        assertNotNull(hc)
        assertEquals(15.25, hc.itsHourlyRate)
        val ws = e.itsSchedule as WeeklySchedule
        assertNotNull(ws)
        val hm = e.itsPaymentMethod as HoldMethod
        assertNotNull(hm)
    }

    @Test
    fun testAddCommissionedEmployee() {
        println("TestAddCommissionedEmployee")
        val empId = 3
        val t = AddCommissionedEmployee(empId, "Lance", "Home", 2500.00, 3.2)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Lance", e.itsName)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        assertEquals(2500.00, cc.itsSalary)
        assertEquals(3.2, cc.itsCommissionRate)
        val bs = e.itsSchedule as BiweeklySchedule
        assertNotNull(bs)
        val hm = e.itsPaymentMethod as HoldMethod
        assertNotNull(hm)
    }
}
