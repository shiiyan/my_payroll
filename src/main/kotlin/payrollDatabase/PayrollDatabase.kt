package payrollDatabase

import payrollDomain.Employee

class PayrollDatabase {
    companion object {
        private val itsEmployees: MutableMap<Int, Employee> = mutableMapOf()
        private val itsUnionMembers: MutableMap<Int, Employee> = mutableMapOf()

        fun addEmployee(empId: Int, e: Employee) {
            itsEmployees[empId] = e
        }

        fun getEmployee(empId: Int) = itsEmployees[empId]

        fun deleteEmployee(empId: Int) = itsEmployees.remove(empId)

        fun clear() {
            itsEmployees.clear()
            itsUnionMembers.clear()
        }

        fun addUnionMember(memberId: Int, e: Employee) {
            itsUnionMembers[memberId] = e
        }

        fun getUnionMember(memberId: Int) = itsUnionMembers[memberId]

        fun removeUnionMember(memberId: Int) = itsUnionMembers[memberId]
    }
}
