package pl.edu.wat.sheets.service

import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import pl.edu.wat.sheets.model.dto.ExpenseDto
import pl.edu.wat.sheets.model.dto.IncomeDto
import pl.edu.wat.sheets.model.dto.SheetDto
import pl.edu.wat.sheets.model.dto.SheetFullDto
import pl.edu.wat.sheets.model.mapper.toDto
import pl.edu.wat.sheets.model.mapper.toEntity
import pl.edu.wat.sheets.repository.ExpenseRepository
import pl.edu.wat.sheets.repository.IncomeRepository
import pl.edu.wat.sheets.repository.SheetRepository

@Service
class SheetService(
        private val sheetRepository: SheetRepository,
        private val expenseRepository: ExpenseRepository,
        private val incomeRepository: IncomeRepository
) {

    fun getAllSheets(): List<SheetFullDto> {
        logger.info("Getting all sheets")
        val sheets = sheetRepository.findAll().map { it.toDto() }
        logger.info("Getted all sheets $sheets")
        return sheets
    }

    fun saveSheet(sheetDto: SheetDto): SheetFullDto {
        logger.info("Saving sheet $sheetDto")
        val saved = sheetRepository.save(sheetDto.toEntity()).toDto()
        logger.info("Saved sheet: $saved")
        return saved
    }

    fun saveExpense(expenseDto: ExpenseDto): ExpenseDto {
        logger.info("Saving expense $expenseDto")
        val sheet = sheetRepository.findByIdOrNull(expenseDto.sheetId)
        logger.info("Found related sheet $sheet")
        sheet?.let {
            val saved = expenseRepository.save(expenseDto.toEntity(it)).toDto()
            logger.info("Saved expense: $saved")
            return saved
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Sheet not found for ID = ${expenseDto.id}")
    }

    fun saveIncome(incomeDto: IncomeDto): IncomeDto {
        logger.info("Saving income $incomeDto")
        val sheet = sheetRepository.findByIdOrNull(incomeDto.sheetId)
        logger.info("Found related sheet $sheet")
        sheet?.let {
            val saved = incomeRepository.save(incomeDto.toEntity(it)).toDto()
            logger.info("Saved income: $saved")
            return saved
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Sheet not found for ID = ${incomeDto.id}")
    }

    fun deleteSheet(sheetId: Long): Boolean {
        val sheet = sheetRepository.findByIdOrNull(sheetId)
        sheet?.let {
            logger.info("Deleting sheet $sheet")
            sheetRepository.delete(sheet)
            logger.info("Sheet deleted: $sheet")
            return true
        }
        return false
    }

    fun deleteIncome(incomeId: Long): Boolean {
        val income = incomeRepository.findByIdOrNull(incomeId)
        income?.let {
            logger.info("Deleting income $income")
            incomeRepository.delete(income)
            logger.info("Income deleted: $income")
            return true
        }
        return false
    }

    fun deleteExpense(expenseId: Long): Boolean {
        val expense = expenseRepository.findByIdOrNull(expenseId)
        expense?.let {
            logger.info("Deleting expense $expense")
            expenseRepository.delete(expense)
            logger.info("Expense deleted: $expense")
            return true
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SheetService::class.java)
    }

}
