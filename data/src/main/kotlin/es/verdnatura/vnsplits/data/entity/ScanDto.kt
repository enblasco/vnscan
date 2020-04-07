package es.verdnatura.vnsplits.data.entity

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
class ScanDto {

    var Id = 0
    var Name = ""
    var Parent = 0
    var Date = ""
    var NumLines = 0
    var Level = 1
    var Childs = ArrayList<ScanDto>()

}