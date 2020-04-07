package es.verdnatura.vnsplits.domain.entity

import javax.inject.Named

/**
 * Created by Enrique Blasco Blanquer on 8/6/17.
 */
class ScanLine() {

    constructor(id: Int, code: String, db: Boolean) : this() {
        this.Id = id
        this.Code = code
        this.Db = db
    }

    constructor(code: String, value: String, info: String, total: Int) : this() {
        this.Code = code
        this.Value = value
        this.Info = info
        this.Total = total
    }

    @Named("Id")
    var Id: Int = 0

    @Named("Code")
    var Code: String = ""

    @Named("Value")
    var Value: String = ""

    @Named("Info")
    var Info: String = ""

    @Named("Delete")
    var Delete: Boolean = false

    @Named("Db")
    var Db: Boolean = false

    @Named("Total")
    var Total: Int = 0
}