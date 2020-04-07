package es.verdnatura.vnsplits.domain.interactor.scanline

import es.verdnatura.vnsplits.domain.entity.ScanLine
import es.verdnatura.vnsplits.domain.parser.Serializer
import es.verdnatura.vnsplits.domain.repository.ScanLineRepository

/**
 * Created by Enrique Blasco Blanquer on 19/6/17.
 */
open class ScanLineBaseTest {

    var json = "[{\"Code\":\"25795\",\"Id\":5,\"Info\":\"MIX EXT Rosaprima\",\"Value\":\"25795 RS D Austin MIX 50\"},{\"Code\":\"25665\",\"Id\":6,\"Info\":\"CRM EXT Rosaprima\",\"Value\":\"25665 RS D Austin PATIENCE 40\"},{\"Code\":\"8561\",\"Id\":7,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"34353\",\"Id\":8,\"Info\":\"BRs EXT Rosaprima\",\"Value\":\"34353 RS Esperance 50\"},{\"Code\":\"8561\",\"Id\":9,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":10,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"55307\",\"Id\":11,\"Info\":\"RSA EXT Rosaprima\",\"Value\":\"55307 RS Geraldine 50\"},{\"Code\":\"8561\",\"Id\":12,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"48637\",\"Id\":13,\"Info\":\"LAV EXT Rosaprima\",\"Value\":\"48637 RS Ocean Song 50\"},{\"Code\":\"45982\",\"Id\":14,\"Info\":\"AMA EXT Rosaprima\",\"Value\":\"45982 RS  Alert 50\"},{\"Code\":\"8761\",\"Id\":15,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8761 RS Freedom 70\"},{\"Code\":\"8761\",\"Id\":16,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8761 RS Freedom 70\"},{\"Code\":\"8561\",\"Id\":17,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":18,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":19,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":22,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":23,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":24,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":25,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"8561\",\"Id\":26,\"Info\":\"ROJ EXT Rosaprima\",\"Value\":\"8561 RS Freedom 50\"},{\"Code\":\"adc\",\"Id\":27},{\"Code\":\"1\",\"Id\":78,\"Info\":\"LIV EXT L\u0027Arenal\",\"Value\":\"1 Euc Cinerea 70\"},{\"Code\":\"1\",\"Id\":79,\"Info\":\"LIV EXT L\u0027Arenal\",\"Value\":\"1 Euc Cinerea 70\"},{\"Code\":\"123456789\",\"Id\":80,\"Value\":\"38634 LIBRE 17\"},{\"Code\":\"125983363\",\"Id\":81,\"Value\":\"1226 Guzmania MIX XL x08 70\"},{\"Code\":\"1\",\"Id\":82,\"Info\":\"LIV EXT L\u0027Arenal\",\"Value\":\"1 Euc Cinerea 70\"}]"

    val uiThread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()
    val thread: io.reactivex.Scheduler = com.nhaarman.mockito_kotlin.mock()
    var repository: ScanLineRepository = com.nhaarman.mockito_kotlin.mock()

    var lines = Serializer().deserializeList(json, ScanLine::class.java)

}