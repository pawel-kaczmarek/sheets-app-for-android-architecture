package pl.edu.wat.sheets.model.mapper

import pl.edu.wat.sheets.model.dto.ExpenseDto
import pl.edu.wat.sheets.model.dto.IncomeDto
import pl.edu.wat.sheets.model.dto.SheetDto
import pl.edu.wat.sheets.model.dto.SheetFullDto
import pl.edu.wat.sheets.model.entity.Expense
import pl.edu.wat.sheets.model.entity.Income
import pl.edu.wat.sheets.model.entity.Sheet
import java.lang.IllegalArgumentException

fun Sheet.toDto() = SheetFullDto(
        sheet = SheetDto(
                id = id,
                title = title,
                description = description
        ),
        expenses = this.expenses.map { it.toDto() }.toSet(),
        incomes = this.incomes.map { it.toDto() }.toSet()
)

fun SheetDto.toEntity() = Sheet(
        id = id,
        title = title,
        description = description
)

fun Expense.toDto() = if (this.sheet.id != null) ExpenseDto(
        id = this.id,
        title = this.title,
        description = this.description,
        costs = this.costs,
        sheetId = this.sheet.id
) else throw IllegalArgumentException("Sheet ID cannot be null")

fun ExpenseDto.toEntity(sheet: Sheet) = Expense(
        id = this.id,
        title = this.title,
        description = this.description,
        costs = this.costs,
        sheet = sheet
)

fun Income.toDto() = if (this.sheet.id != null) IncomeDto(
        id = this.id,
        title = this.title,
        description = this.description,
        costs = this.costs,
        sheetId = this.sheet.id
) else throw IllegalArgumentException("Sheet ID cannot be null")

fun IncomeDto.toEntity(sheet: Sheet) = Income(
        id = this.id,
        title = this.title,
        description = this.description,
        costs = this.costs,
        sheet = sheet
)