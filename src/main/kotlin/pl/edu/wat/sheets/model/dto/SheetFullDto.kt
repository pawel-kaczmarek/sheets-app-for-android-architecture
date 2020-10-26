package pl.edu.wat.sheets.model.dto

data class SheetFullDto(
        val sheet: SheetDto,
        val expenses: Set<ExpenseDto>,
        val incomes: Set<IncomeDto>
)
