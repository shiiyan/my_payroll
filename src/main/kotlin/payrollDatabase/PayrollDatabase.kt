package payrollDatabase

import payrollDomain.Employee

class PayrollDatabase {
    companion object {
        private val itsEmployees: MutableMap<Int, Employee> = mutableMapOf()

        fun addEmployee(empId: Int, e: Employee) {
            itsEmployees.put(empId, e)
        }

        fun getEmployee(empId: Int) = itsEmployees[empId]

        fun deleteEmployee(empId: Int) = itsEmployees.remove(empId)

        fun clear() {
            itsEmployees.clear()
        }
    }
}
