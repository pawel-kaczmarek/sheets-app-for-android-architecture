package pl.edu.wat.sheets.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.edu.wat.sheets.model.dto.ExpenseDto
import pl.edu.wat.sheets.model.dto.IncomeDto
import pl.edu.wat.sheets.model.dto.SheetDto
import pl.edu.wat.sheets.model.dto.SheetFullDto
import pl.edu.wat.sheets.model.entity.Sheet
import pl.edu.wat.sheets.service.SheetService

@RestController
@RequestMapping("/api")
class SheetController(
        private val sheetService: SheetService
) {

    @GetMapping("/sheet", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun getSheets(): ResponseEntity<List<SheetFullDto>> {
        val allSheets = sheetService.getAllSheets()
        return ResponseEntity.ok(allSheets)
    }

    @PostMapping("/sheet", consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postSheet(@RequestBody sheetDto: SheetDto): ResponseEntity<SheetFullDto> {
        val saved = sheetService.saveSheet(sheetDto)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/expense", consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postExpense(@RequestBody expenseDto: ExpenseDto): ResponseEntity<ExpenseDto> {
        val saved = sheetService.saveExpense(expenseDto)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/income", consumes = ["application/json"], produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postIncome(@RequestBody incomeDto: IncomeDto): ResponseEntity<IncomeDto> {
        val saved = sheetService.saveIncome(incomeDto)
        return ResponseEntity.ok(saved)
    }

}
