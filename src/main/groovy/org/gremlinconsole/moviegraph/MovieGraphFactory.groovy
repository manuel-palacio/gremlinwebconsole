package org.gremlinconsole.moviegraph

import com.tinkerpop.blueprints.Graph
import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import com.tinkerpop.gremlin.groovy.Gremlin

public class MovieGraphFactory {


    static Graph movieGraph


    static def createMovieGraph() {

        if(movieGraph && movieGraph.getEdges().iterator().next()) return movieGraph

        Gremlin.load()

        movieGraph = new TinkerGraph()



        new File("data/movies.dat").eachLine {
            def elements = it.split("\\|")
            movieGraph.addVertex(['type': 'Movie', 'movieId': elements[0].toInteger(), 'title': elements[1]])

        }

        new File('data/u.user').eachLine {def line ->
            def components = line.split('\\|')
            movieGraph.addVertex(['type': 'User', 'userId': components[0].toInteger(), 'gender': components[2], 'age': components[1].toInteger()])
        }

        new File('data/u2.data').eachLine {def line ->
            def components = line.split('\\t')
            def ratedEdge = movieGraph.addEdge(movieGraph.V('userId',components[0].toInteger()).next(), movieGraph.V('movieId', components[1].toInteger()).next(), 'rated');
            ratedEdge.setProperty('stars', components[2].toInteger());
        }

        movieGraph

    }
}
