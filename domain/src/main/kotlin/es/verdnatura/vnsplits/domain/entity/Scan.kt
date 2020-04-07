package es.verdnatura.vnsplits.domain.entity

import java.util.ArrayList
import javax.inject.Named

/**
 * Created by Enrique Blasco Blanquer on 22/5/17.
 */
class Scan {

    @Named("Id")
    var Id = 0

    @Named("Name")
    var Name = ""

    @Named("Date")
    var Date = ""

    @Named("Parent")
    var Parent = 0

    @Named("Level")
    var Level = 1

    @Named("NumLines")
    var NumLines = 0

    @Named("Childs")
    var Childs: List<Scan> = ArrayList()

    @Named("Displayed")
    var Displayed = false

}