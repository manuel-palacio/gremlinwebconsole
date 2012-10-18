package org.gremlinconsole.moviegraph

import com.tinkerpop.blueprints.Graph
import com.tinkerpop.blueprints.impls.tg.TinkerGraph

public class MovieGraphFactory {


    static Graph graph


    static def createMovieGraph() {

        if(graph !=  null) return graph

        graph = new TinkerGraph()



        new File("data/movies.dat").eachLine {
            def elements = it.split("\\|")
            graph.addVertex(['type': 'Movie', 'movieId': elements[0].toInteger(), 'title': elements[1]])

        }

        new File('data/u.user').eachLine {def line ->
            def components = line.split('\\|')
            graph.addVertex(['type': 'User', 'userId': components[0].toInteger(), 'gender': components[2], 'age': components[1].toInteger()])
        }

        new File('data/u2.data').eachLine {def line ->
            def components = line.split('\\t')
            def ratedEdge = graph.addEdge(graph.V('userId',components[0].toInteger()).next(), graph.V('movieId', components[1].toInteger()).next(), 'rated');
            ratedEdge.setProperty('stars', components[2].toInteger());
        }

        graph

    }
}
