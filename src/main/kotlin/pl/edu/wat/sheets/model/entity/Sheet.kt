package pl.edu.wat.sheets.model.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Sheet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val description: String
) {
    @OneToMany(mappedBy = "sheet", cascade = [CascadeType.ALL])
    val expenses = mutableSetOf<Expense>()

    @OneToMany(mappedBy = "sheet", cascade = [CascadeType.ALL])
    val incomes = mutableSetOf<Income>()
}
