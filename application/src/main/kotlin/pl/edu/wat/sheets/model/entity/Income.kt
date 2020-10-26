package pl.edu.wat.sheets.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Income(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val description: String,
        val costs: Double,

        @ManyToOne
        @JoinColumn(referencedColumnName = "id")
        val sheet: Sheet
)
