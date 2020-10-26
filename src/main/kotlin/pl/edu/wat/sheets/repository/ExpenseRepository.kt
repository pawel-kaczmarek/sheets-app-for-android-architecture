package pl.edu.wat.sheets.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.edu.wat.sheets.model.entity.Expense

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long>
