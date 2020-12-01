package pl.edu.wat.sheets

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.edu.wat.sheets.model.entity.Expense
import pl.edu.wat.sheets.model.entity.Income
import pl.edu.wat.sheets.model.entity.Sheet
import pl.edu.wat.sheets.repository.SheetRepository

@Configuration
class SampleDataLoader {

    @Bean
    fun demoData(sheetRepository: SheetRepository): CommandLineRunner {
        return CommandLineRunner {
            for (i in 0..4) {
                sheetRepository.save(createRandomSheet(i))
            }
        }
    }

    private fun createRandomSheet(value: Int): Sheet {
        val sheet = Sheet(
                title = "Sheet $value",
                description = "Description $value"
        )
        for (v in 1..value) {
            sheet.incomes.add(Income(
                    title = "Income title $value$v",
                    description = "Income description $value$v",
                    costs = value.toDouble(),
                    sheet = sheet
            ))

            sheet.expenses.add(Expense(
                    title = "Expense title $value$v",
                    description = "Expense description $value$v",
                    costs = value.toDouble(),
                    sheet = sheet
            ))
        }

        return sheet
    }

}