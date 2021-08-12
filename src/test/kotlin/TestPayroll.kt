import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import payrollDatabase.PayrollDatabase
import payrollDomainImplementation.affiliation.NoAffiliation
import payrollDomainImplementation.affiliation.UnionAffiliation
import payrollDomainImplementation.paymentClassification.CommissionedClassification
import payrollDomainImplementation.paymentClassification.HourlyClassification
import payrollDomainImplementation.paymentClassification.SalariedClassification
import payrollDomainImplementation.paymentMethod.DirectMethod
import payrollDomainImplementation.paymentMethod.HoldMethod
import payrollDomainImplementation.paymentMethod.MailMethod
import payrollDomainImplementation.paymentSchedule.BiweeklySchedule
import payrollDomainImplementation.paymentSchedule.MonthlySchedule
import payrollDomainImplementation.paymentSchedule.WeeklySchedule
import transactionImplementation.AddCommissionedEmployeeTransaction
import transactionImplementation.AddHourlyEmployeeTransaction
import transactionImplementation.AddSalariedEmployeeTransaction
import transactionImplementation.AddSalesReceiptTransaction
import transactionImplementation.AddServiceChargeTransaction
import transactionImplementation.AddTimeCardTransaction
import transactionImplementation.ChangeAddressTransaction
import transactionImplementation.ChangeCommissionedClassificationTransaction
import transactionImplementation.ChangeDirectMethodTransaction
import transactionImplementation.ChangeHoldMethodTransaction
import transactionImplementation.ChangeHourlyClassificationTransaction
import transactionImplementation.ChangeMailMethodTransaction
import transactionImplementation.ChangeNameTransaction
import transactionImplementation.ChangeSalariedClassificationTransaction
import transactionImplementation.ChangeUnaffiliatedTransaction
import transactionImplementation.ChangeUnionMemberTransaction
import transactionImplementation.DeleteEmployeeTransaction
import java.lang.IllegalArgumentException
import java.util.Calendar
import java.util.GregorianCalendar
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
    fun testValidateAddSalariedEmployee() {
        println("TestValidateAddSalariedEmployee")
        run {
            val empId = 1
            val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.00)
            t.validate()
        }

        run {
            val empId = 2
            val t = AddSalariedEmployeeTransaction(empId, "", "Home", 1000.00)
            assertThrows<IllegalArgumentException> { t.validate() }
        }

        run {
            val empId = 3
            val t = AddSalariedEmployeeTransaction(empId, "Bob", "", 1000.00)
            assertThrows<IllegalArgumentException> { t.validate() }
        }
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
        val defaultDate = GregorianCalendar(2021, Calendar.AUGUST, 11)
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
        val defaultDate = GregorianCalendar(2021, Calendar.AUGUST, 11)
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
        val defaultDate = GregorianCalendar(2021, Calendar.AUGUST, 11)
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

    @Test
    fun testChangeNameTransaction() {
        println("TestChangeName")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cnt = ChangeNameTransaction(empId, "Bob")
        cnt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Bob", e.itsName)
    }

    @Test
    fun testChangeAddressTransaction() {
        println("TestChangeAddress")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cnt = ChangeAddressTransaction(empId, "Work")
        cnt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Work", e.itsAddress)
    }

    @Test
    fun testChangeHourlyClassificationTransaction() {
        println("TestChangeHourlyClassification")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.00, 3.2)
        t.execute()
        val cht = ChangeHourlyClassificationTransaction(empId, 27.52)
        cht.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val hc = e.itsClassification as HourlyClassification
        assertNotNull(hc)
        assertEquals(27.52, hc.itsHourlyRate)
        val ws = e.itsSchedule as WeeklySchedule
        assertNotNull(ws)
    }

    @Test
    fun testChangeSalariedClassificationTransaction() {
        println("TestChangeSalariedClassification")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.00, 3.2)
        t.execute()
        val cst = ChangeSalariedClassificationTransaction(empId, 1500.00)
        cst.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val sc = e.itsClassification as SalariedClassification
        assertNotNull(sc)
        assertEquals(1500.00, sc.itsSalary)
        val ms = e.itsSchedule as MonthlySchedule
        assertNotNull(ms)
    }

    @Test
    fun testChangeCommissionedClassificationTransaction() {
        println("TestChangeCommissionedClassification")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cct = ChangeCommissionedClassificationTransaction(empId, 1600.00, 7.50)
        cct.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        assertEquals(1600.00, cc.itsSalary)
        assertEquals(7.50, cc.itsCommissionRate)
        val bs = e.itsSchedule as BiweeklySchedule
        assertNotNull(bs)
    }

    @Test
    fun testChangeDirectMethodTransaction() {
        println("TestChangeDirectMethodTransaction")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cdt = ChangeDirectMethodTransaction(empId, "FirstNational", "1058209")
        cdt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val dm = e.itsPaymentMethod as DirectMethod
        assertNotNull(dm)
        assertEquals("FirstNational", dm.itsBank)
        assertEquals("1058209", dm.itsAccount)
    }

    @Test
    fun testChangeHoldMethodTransaction() {
        println("TestChangeHoldMethodTransaction")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cdt = ChangeDirectMethodTransaction(empId, "FirstNational", "1058209")
        cdt.execute()
        val cht = ChangeHoldMethodTransaction(empId)
        cht.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val hm = e.itsPaymentMethod as HoldMethod
        assertNotNull(hm)
    }

    @Test
    fun testChangeMailMethodTransaction() {
        println("TestChangeMailMethodTransaction")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cmt = ChangeMailMethodTransaction(empId, "4080 El Cerrito Road")
        cmt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val mm = e.itsPaymentMethod as MailMethod
        assertNotNull(mm)
        assertEquals("4080 El Cerrito Road", mm.itsAddress)
    }

    @Test
    fun testChangeUnionMemberTransaction() {
        println("TestChangeUnionMemberTransaction")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val memberId = 7734
        val cut = ChangeUnionMemberTransaction(empId, memberId, 99.42)
        cut.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val ua = e.itsAffiliation as UnionAffiliation
        assertNotNull(ua)
        assertEquals(memberId, ua.itsMemberId)
        assertEquals(99.42, ua.itsDues)
        val um = PayrollDatabase.getUnionMember(memberId)
        assertNotNull(um)
        assertEquals(e, um)
    }

    @Test
    fun testChangeUnaffiliatedTransaction() {
        println("TestChangeUnaffiliatedTransaction")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val memberId = 7734
        val cmt = ChangeUnionMemberTransaction(empId, memberId, 99.42)
        cmt.execute()
        val cut = ChangeUnaffiliatedTransaction(empId)
        cut.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val na = e.itsAffiliation as NoAffiliation
        assertNotNull(na)
        val um = PayrollDatabase.getUnionMember(memberId)
        assertNull(um)
    }
}
