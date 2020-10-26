package pl.edu.wat.sheets.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pl.edu.wat.sheets.model.entity.Sheet

@Repository
interface SheetRepository : JpaRepository<Sheet, Long>
