package org.gremlinconsole

import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph
import com.tinkerpop.gremlin.Tokens
import com.tinkerpop.gremlin.groovy.Gremlin

class MovieGraphFactory {


    static TinkerGraph graph

    static TinkerGraph createGraph() {

        if (graph) {
            return graph
        }
        Gremlin.load()

        def genre = [:]
        graph = new TinkerGraph()


        new FileInputStream("data/u.genre").eachLine {
            def elements = it.split("\\|")
            if (elements.size() == 2) {
                genre.put(elements[1], elements[0])
            }
        }

        new FileInputStream("data/u.item").eachLine {
            def elements = it.split("\\|")
            def movieVertex = graph.addVertex(['type': 'Movie', 'movieId': elements[0].toInteger(), 'title': elements[1]])
            int counter = 0
            elements.each {
                if (it == "1") {
                    def genera = genre.get(counter.toString())
                    if (genera) {
                        def hits = graph.idx(Tokens.T.v)[[genera: genera]].iterator()
                        def generaVertex = hits.hasNext() ? hits.next() : graph.addVertex(['type': 'Genera', 'genera': genera]);
                        graph.addEdge(movieVertex, generaVertex, 'hasGenera');
                    }
                }
                counter++
            }
        }

        new FileInputStream('data/u.user').eachLine {def line ->
            def components = line.split('\\|')
            graph.addVertex(['type': 'User', 'userId': components[0].toInteger(), 'gender': components[2], 'age': components[1].toInteger()]);
        }

        new FileInputStream('data/u.data').eachLine {def line ->
            def components = line.split('\\t')
            def ratedEdge = graph.addEdge(graph.idx(Tokens.T.v)[[userId: components[0].toInteger()]].next(), graph.idx(Tokens.T.v)[[movieId: components[1].toInteger()]].next(), 'rated');
            ratedEdge.setProperty('stars', components[2].toInteger());
        }


        return graph

    }
}
