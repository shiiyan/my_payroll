package payroll

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import payroll.database.PayrollDatabase
import payroll.domainImplementation.affiliation.NoAffiliation
import payroll.domainImplementation.affiliation.UnionAffiliation
import payroll.domainImplementation.paymentClassification.CommissionedClassification
import payroll.domainImplementation.paymentClassification.HourlyClassification
import payroll.domainImplementation.paymentClassification.SalariedClassification
import payroll.domainImplementation.paymentMethod.DirectMethod
import payroll.domainImplementation.paymentMethod.HoldMethod
import payroll.domainImplementation.paymentMethod.MailMethod
import payroll.domainImplementation.paymentSchedule.BiweeklySchedule
import payroll.domainImplementation.paymentSchedule.MonthlySchedule
import payroll.domainImplementation.paymentSchedule.WeeklySchedule
import payroll.factoryImplementation.SimpleFactory
import payroll.transactionImplementation.AddCommissionedEmployeeTransaction
import payroll.transactionImplementation.AddHourlyEmployeeTransaction
import payroll.transactionImplementation.AddSalariedEmployeeTransaction
import payroll.transactionImplementation.AddSalesReceiptTransaction
import payroll.transactionImplementation.AddServiceChargeTransaction
import payroll.transactionImplementation.AddTimeCardTransaction
import payroll.transactionImplementation.ChangeAddressTransaction
import payroll.transactionImplementation.ChangeCommissionedClassificationTransaction
import payroll.transactionImplementation.ChangeDirectMethodTransaction
import payroll.transactionImplementation.ChangeHoldMethodTransaction
import payroll.transactionImplementation.ChangeHourlyClassificationTransaction
import payroll.transactionImplementation.ChangeMailMethodTransaction
import payroll.transactionImplementation.ChangeNameTransaction
import payroll.transactionImplementation.ChangeSalariedClassificationTransaction
import payroll.transactionImplementation.ChangeUnaffiliatedTransaction
import payroll.transactionImplementation.ChangeUnionMemberTransaction
import payroll.transactionImplementation.DeleteEmployeeTransaction
import payroll.transactionImplementation.PaydayTransaction
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
        val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.0)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Bob", e.itsName)
        val sc = e.itsClassification as SalariedClassification
        assertNotNull(sc)
        assertEquals(1000.0, sc.itsSalary)
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
            val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.0)
            t.validate()
        }

        run {
            val empId = 2
            val t = AddSalariedEmployeeTransaction(empId, "", "Home", 1000.0)
            assertThrows<IllegalArgumentException> { t.validate() }
        }

        run {
            val empId = 3
            val t = AddSalariedEmployeeTransaction(empId, "Bob", "", 1000.0)
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
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.0, 3.2)
        t.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        assertEquals("Lance", e.itsName)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        assertEquals(2500.0, cc.itsSalary)
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
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.0, 3.2)
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
        val tct = AddTimeCardTransaction(defaultDate, 8.0, empId)
        tct.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val hc = e.itsClassification as HourlyClassification
        assertNotNull(hc)
        val tc = hc.getTimeCard(defaultDate)
        assertNotNull(tc)
        assertEquals(8.0, tc.itsHours)
    }

    @Test
    fun testAddSalesReceipt() {
        println("TestAddSalesReceipt")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.0, 3.2)
        t.execute()
        val defaultDate = GregorianCalendar(2021, Calendar.AUGUST, 11)
        val srt = AddSalesReceiptTransaction(defaultDate, 25000.0, empId)
        srt.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        val sr = cc.getReceipt(defaultDate)
        assertNotNull(sr)
        assertEquals(25000.0, sr.itsAmount)
    }

    @Test
    fun testAddServiceCharge() {
        println("TestAddServiceCharge")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val defaultDate = GregorianCalendar(2021, Calendar.AUGUST, 11)
        val tct = AddTimeCardTransaction(defaultDate, 8.0, empId)
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
    fun testChangeName() {
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
    fun testChangeAddress() {
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
    fun testChangeHourlyClassification() {
        println("TestChangeHourlyClassification")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.0, 3.2)
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
    fun testChangeSalariedClassification() {
        println("TestChangeSalariedClassification")
        val empId = 3
        val t = AddCommissionedEmployeeTransaction(empId, "Lance", "Home", 2500.0, 3.2)
        t.execute()
        val cst = ChangeSalariedClassificationTransaction(empId, 1500.0)
        cst.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val sc = e.itsClassification as SalariedClassification
        assertNotNull(sc)
        assertEquals(1500.0, sc.itsSalary)
        val ms = e.itsSchedule as MonthlySchedule
        assertNotNull(ms)
    }

    @Test
    fun testChangeCommissionedClassification() {
        println("TestChangeCommissionedClassification")
        val empId = 2
        val t = AddHourlyEmployeeTransaction(empId, "Bill", "Home", 15.25)
        t.execute()
        val cct = ChangeCommissionedClassificationTransaction(empId, 1600.0, 7.50)
        cct.execute()

        val e = PayrollDatabase.getEmployee(empId)
        assertNotNull(e)
        val cc = e.itsClassification as CommissionedClassification
        assertNotNull(cc)
        assertEquals(1600.0, cc.itsSalary)
        assertEquals(7.50, cc.itsCommissionRate)
        val bs = e.itsSchedule as BiweeklySchedule
        assertNotNull(bs)
    }

    @Test
    fun testChangeDirectMethod() {
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
    fun testChangeHoldMethod() {
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
    fun testChangeMailMethod() {
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
    fun testChangeUnionMember() {
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
    fun testChangeUnaffiliated() {
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

    @Test
    fun testPaySingleSalariedEmployee() {
        println("TestPaySingleSalariedEmployee")
        val empId = 1
        val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.0)
        t.execute()
        val payDate = GregorianCalendar(2021, Calendar.JULY, 25)
        val pt = PaydayTransaction(itsPaydate = payDate, itsFactory = SimpleFactory())
        pt.execute()
        val payPeriodStartDate = GregorianCalendar(2021, Calendar.JUNE, 1)
        val payPeriodEndDate = GregorianCalendar(2021, Calendar.JUNE, 30)
        validatePaycheck(
            pt = pt,
            empId = empId,
            payPeriodStartDate = payPeriodStartDate,
            payPeriodEndDate = payPeriodEndDate,
            pay = 1000.0
        )
    }

    @Test
    fun testPaySingleSalariedEmployeeOnWrongDate() {
        println("TestPaySingleSalariedEmployee")
        val empId = 1
        val t = AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.0)
        t.execute()
        val payDate = GregorianCalendar(2021, Calendar.JULY, 20)
        val pt = PaydayTransaction(itsPaydate = payDate, itsFactory = SimpleFactory())
        pt.execute()
        val pc = pt.getPaycheck(empId)
        assertNull(pc)
    }

    private fun validatePaycheck(
        pt: PaydayTransaction,
        empId: Int,
        payPeriodStartDate: Calendar,
        payPeriodEndDate: Calendar,
        pay: Double
    ) {
        val pc = pt.getPaycheck(empId)
        assertNotNull(pc)
        assertEquals(payPeriodStartDate, pc.getPayPeriodStartDate())
        assertEquals(payPeriodEndDate, pc.getPayPeriodEndDate())
        assertEquals(pay, pc.getGrossPay())
        assertEquals("Hold", pc.getField("Deposition"))
        assertEquals(0.0, pc.getDeductions())
        assertEquals(pay, pc.getNetPay())
    }
}
