package payroll.database

import payroll.domain.Employee

interface PayrollDatabase {
    fun addEmployee(empId: Int, e: Employee)
    fun getEmployee(empId: Int): Employee?
    fun deleteEmployee(empId: Int): Employee?
    fun clear()
    fun addUnionMember(memberId: Int, e: Employee)
    fun getUnionMember(memberId: Int): Employee?
    fun removeUnionMember(memberId: Int): Employee?
    fun getAllEmployeeIds(): MutableSet<Int>
}
