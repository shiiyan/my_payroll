package payroll

import payroll.application.PayrollApplication
import payroll.database.GlobalDatabase
import payroll.database.InMemoryPayrollDatabase
import payroll.transactionSource.TextParserTransactionSource

class TestMain {
    companion object {
        fun test() {
            GlobalDatabase.payrollDatabase = InMemoryPayrollDatabase()
            val commandLineList = getCommandLinesFromFile("META-INF/main.txt")
            commandLineList.forEach {
                val textSource = TextParserTransactionSource(itsCommandLine = it)
                val app = PayrollApplication(
                    itsTransactionSource = textSource
                )
                app.main()
            }
        }

        private fun getCommandLinesFromFile(fileSource: String): List<List<String>> {
            TODO("Not yet implemented.")
        }
    }
}
