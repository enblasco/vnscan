package es.verdnatura.vnsplits.data.repository

import es.verdnatura.vnsplits.domain.entity.Scan
import es.verdnatura.vnsplits.domain.parser.Serializer
import es.verdnatura.vnsplits.domain.repository.ScanRepository
import io.reactivex.Observable

/**
 * Created by Enrique Blasco Blanquer on 5/6/17.
 */
class ScanRepositoryTest: ScanRepository {
    override fun getVersion(): Observable<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var json = "[{\"Childs\":[],\"Date\":\"2016-08-09 12:56:19\",\"Id\":1,\"Level\":1,\"Name\":\"madrid\",\"NumLines\":20,\"Parent\":0},{\"Childs\":[{\"Childs\":[{\"Childs\":[],\"Date\":\"2017-03-30 09:23:28\",\"Id\":16,\"Level\":3,\"Name\":\"CCCCCCC\",\"NumLines\":0,\"Parent\":3},{\"Childs\":[],\"Date\":\"2017-03-30 09:23:28\",\"Id\":17,\"Level\":3,\"Name\":\"DDDDDDD\",\"NumLines\":0,\"Parent\":3}],\"Date\":\"2016-08-09 12:56:43\",\"Id\":3,\"Level\":2,\"Name\":\"palet 1\",\"NumLines\":4,\"Parent\":2},{\"Childs\":[],\"Date\":\"2017-03-30 09:23:28\",\"Id\":14,\"Level\":2,\"Name\":\"AAAAAAA\",\"NumLines\":0,\"Parent\":2},{\"Childs\":[],\"Date\":\"2017-03-30 09:23:28\",\"Id\":15,\"Level\":2,\"Name\":\"BBBBBBBB\",\"NumLines\":0,\"Parent\":2},{\"Childs\":[],\"Date\":\"2017-03-30 09:23:28\",\"Id\":18,\"Level\":2,\"Name\":\"EEEEEEEE\",\"NumLines\":0,\"Parent\":2}],\"Date\":\"2016-08-09 12:56:25\",\"Id\":2,\"Level\":1,\"Name\":\"silla\",\"NumLines\":4,\"Parent\":0},{\"Childs\":[],\"Date\":\"2017-03-11 18:21:13\",\"Id\":11,\"Level\":1,\"Name\":\"aeropuerto sáb 11\",\"NumLines\":0,\"Parent\":0},{\"Childs\":[{\"Childs\":[],\"Date\":\"2017-03-13 19:08:58\",\"Id\":13,\"Level\":2,\"Name\":\"b\",\"NumLines\":0,\"Parent\":12}],\"Date\":\"2017-03-13 19:08:03\",\"Id\":12,\"Level\":1,\"Name\":\"a\",\"NumLines\":0,\"Parent\":0}]"

    override fun getScans(type: String): Observable<List<Scan>> {
        val serializer = Serializer()
        return Observable.just(serializer.deserializeList(json, Scan::class.java))
    }

    override fun addScan(type: String, parent: Int, name: String): Observable<Int> {
        return Observable.just(20)
    }

    override fun deleteScan(id: Int): Observable<Void> {
        return Observable.empty()
    }

    override fun getWarehouses(): Observable<List<String>> {
        return Observable.just(listOf("Madrid", "SillaFv", "SillaPca"))
    }
}