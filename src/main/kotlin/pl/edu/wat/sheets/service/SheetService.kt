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
        return sheetRepository.findAll().map { it.toDto() }
    }

    fun saveSheet(sheetDto: SheetDto): SheetFullDto {
        logger.info("Saving sheet $sheetDto")
        return sheetRepository.save(sheetDto.toEntity()).toDto()
    }

    fun saveExpense(expenseDto: ExpenseDto): ExpenseDto {
        logger.info("Saving expense $expenseDto")
        val sheet = sheetRepository.findByIdOrNull(expenseDto.sheetId)
        sheet?.let {
            return expenseRepository.save(expenseDto.toEntity(it)).toDto()
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Sheet not found for ID = ${expenseDto.id}")
    }

    fun saveIncome(incomeDto: IncomeDto): IncomeDto {
        logger.info("Saving income $incomeDto")
        val sheet = sheetRepository.findByIdOrNull(incomeDto.sheetId)
        sheet?.let {
            return incomeRepository.save(incomeDto.toEntity(it)).toDto()
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Sheet not found for ID = ${incomeDto.id}")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SheetService::class.java)
    }

}
