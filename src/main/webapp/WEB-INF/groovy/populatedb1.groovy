import com.google.appengine.api.datastore.Entity

//def genre = [:]

/*
new File("data/u.genre").eachLine {
    def elements = it.split("\\|")
    if (elements.size() == 2) {
       def entity = new Entity("Genre")
        entity.type = elements[0]
        entity.genreId = elements[1]
        entity.save()

        genre.put(elements[1], elements[0])

    }
}
*/

new File("data/u.item").eachLine {
    def components = it.split("\\|")
    //def movieVertex = graph.addVertex(['type': 'Movie', 'movieId': elements[0].toInteger(), 'title': elements[1]])
    def entity = new Entity("Item")
    entity.type = "Movie"
    entity.movieId = components[0].toInteger()
    entity.title = components[1]

    entity.save()
    /*int counter = 0
    components.each {
        if (it == "1") {
            def genera = genre.get(counter.toString())
            if (genera) {
                def generaEntity = new Entity("Genera")
                generaEntity.type = "Genera"
                generaEntity.movieId = components[0].toInteger()
                generaEntity.genera = genera
                generaEntity.save()
                  def hits = graph.idx(Tokens.T.v)[[genera: genera]].iterator()
                  def generaVertex = hits.hasNext() ? hits.next() : graph.addVertex(['type': 'Genera', 'genera': genera]);
                 graph.addEdge(movieVertex, generaVertex, 'hasGenera');
            }
        }
        counter++
    }            */
}

