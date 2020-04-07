package es.verdnatura.vnsplits.data.database

import es.verdnatura.core.database.VnOpenHelper
import es.verdnatura.vnsplits.domain.entity.ScanLine
import org.jetbrains.anko.db.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Enrique Blasco Blanquer on 9/6/17.
 */
@Singleton
class ScanLineDatabase{

    private val TABLELINE = "TABLELINE"
    private val ROW_ID = "Id"
    private val ROW_SCAN = "Scan"
    private val ROW_CODE = "Code"

    val db: VnOpenHelper

    @Inject
    constructor(db: VnOpenHelper) {
        this.db = db
        createTables()
    }

    fun createTables(){
        db.use {
            createTable(TABLELINE, true,
                    ROW_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                    ROW_SCAN to INTEGER,
                    ROW_CODE to TEXT)
        }
    }

    fun dropTable(){
        db.use{
            dropTable(TABLELINE, true)
        }
    }

    fun createLines(scan: Int, code: String): Int {
        var id: Long = 0
        db.use {
            id = insert(TABLELINE,
                    ROW_SCAN to scan,
                    ROW_CODE to code)
        }
        return id.toInt()
    }

    fun deleteLines(ids: List<Int>) {
        if(ids.size == 0) return
        var ids = ids.map { id -> id.toString() }
                .fold("") { s, its -> s + its + "," }

        var idsString = ids.substring(0, ids.length -1)

        db.use {
            delete(TABLELINE, "${ROW_ID} in (${idsString})", arrayOf())
        }
    }

    fun listScanLine(scan: Int): List<ScanLine> {
        var codes = listOf<ScanLine>()
        var parser = ScanLineParser()
        db.use {
            select(TABLELINE, ROW_ID, ROW_CODE).whereSimple(ROW_SCAN + " = ?", scan.toString()).exec {
                codes = parseList(parser)
            }
        }
        return codes
    }

    class ScanLineParser: MapRowParser<ScanLine> {
        override fun parseRow(columns: Map<String, Any?>): ScanLine {
            return ScanLine((columns["Id"] as Long).toInt(), columns["Code"] as String, true)
        }
    }

}