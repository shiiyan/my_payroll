import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import payrollDatabase.PayrollDatabase
import payrollImplementation.affiliation.UnionAffiliation
import payrollImplementation.paymentClassification.CommissionedClassification
import payrollImplementation.paymentClassification.HourlyClassification
import payrollImplementation.paymentClassification.SalariedClassification
import payrollImplementation.paymentMethod.HoldMethod
import payrollImplementation.paymentSchedule.BiweeklySchedule
import payrollImplementation.paymentSchedule.MonthlySchedule
import payrollImplementation.paymentSchedule.WeeklySchedule
import transactionImplementation.AddCommissionedEmployeeTransaction
import transactionImplementation.AddHourlyEmployeeTransaction
import transactionImplementation.AddSalariedEmployeeTransaction
import transactionImplementation.AddSalesReceiptTransaction
import transactionImplementation.AddServiceChargeTransaction
import transactionImplementation.AddTimeCardTransaction
import transactionImplementation.DeleteEmployeeTransaction
import java.util.Calendar
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TestPayroll {
    @BeforeEach
    fun setUp() {
        PayrollDatabase.clear()
    }

    @Test
    fun testAddSalariedEmployee() {
        println("TestAddSalariedEmployee")
        val empId = 1
        val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.00)
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
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
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
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.00, 3.2)
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

    @Test
    fun testDeleteEmployee() {
        println("TestDeleteEmployee")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.00, 3.2)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)

        val dt = DeleteEmployeeTransaction(e.itsEmpId)
        dt.execute()

        val de = PayrollDatabase.getEmployee(e.itsEmpId)
        assertNull(de)
    }

    @Test
    fun testAddTimeCardTransaction() {
        println("TestAddTimeCard")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val defaultDate = Calendar.getInstance()
        defaultDate.set(2021, 8, 11)
        val tct = AddTimeCardTransaction(defaultDate, 8.00, empId)
        tct.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val hc = e.itsClassification as HourlyClassification
        assertNotNull(hc)
        val tc = hc.getTimeCard(defaultDate)
        assertNotNull(tc)
        assertEquals(8.00, tc.itsHours)
    }

    @Test
    fun testAddSalesReceipt() {
        println("TestAddSalesReceipt")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.00, 3.2)
        t.execute()
        val defaultDate = Calendar.getInstance()
        defaultDate.set(2021, 8, 11)
        val srt = AddSalesReceiptTransaction(defaultDate, 25000.00, empId)
        srt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        val sr = cc.getReceipt(defaultDate)
        assertNotNull(sr)
        assertEquals(25000.00, sr.itsAmount)
    }

    @Test
    fun testAddServiceCharge() {
        println("TestAddServiceCharge")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val defaultDate = Calendar.getInstance()
        defaultDate.set(2021, 8, 11)
        val tct = AddTimeCardTransaction(defaultDate, 8.00, empId)
        tct.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)

        val memberId = 86
        e.itsAffiliation = UnionAffiliation(12.50, memberId)
        PayrollDatabase.addUnionMember(memberId, e)
        val sct = AddServiceChargeTransaction(memberId, defaultDate, 12.95)
        sct.execute()

        val ua = e.itsAffiliation as UnionAffiliation
        val sc = ua.getServiceCharge(defaultDate)
        assertEquals(12.95, sc)
    }
}
