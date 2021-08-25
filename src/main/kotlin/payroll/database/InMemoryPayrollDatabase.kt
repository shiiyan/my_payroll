package payroll.database

import payroll.domain.Employee

class InMemoryPayrollDatabase : PayrollDatabase {
    private val itsEmployees: MutableMap<Int, Employee> = mutableMapOf()
    private val itsUnionMembers: MutableMap<Int, Employee> = mutableMapOf()

    override fun addEmployee(empId: Int, e: Employee) {
        itsEmployees[empId] = e
    }

    override fun getEmployee(empId: Int) = itsEmployees[empId]

    override fun deleteEmployee(empId: Int) = itsEmployees.remove(empId)

    override fun clear() {
        itsEmployees.clear()
        itsUnionMembers.clear()
    }

    override fun addUnionMember(memberId: Int, e: Employee) {
        itsUnionMembers[memberId] = e
    }

    override fun getUnionMember(memberId: Int) = itsUnionMembers[memberId]

    override fun removeUnionMember(memberId: Int) = itsUnionMembers.remove(memberId)

    override fun getAllEmployeeIds() = itsEmployees.keys
}
