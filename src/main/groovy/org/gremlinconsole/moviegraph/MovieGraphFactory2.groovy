package org.gremlinconsole.moviegraph

import org.springframework.stereotype.Component
import com.googlecode.objectify.util.DAOBase
import com.googlecode.objectify.ObjectifyService
import com.tinkerpop.blueprints.pgm.Graph
import javax.annotation.PostConstruct
import com.tinkerpop.gremlin.groovy.Gremlin
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph
import com.googlecode.objectify.Query
import com.tinkerpop.gremlin.Tokens

//@Component("movieGraphFactory")
public class MovieGraphFactory2 extends DAOBase {

    static {
        ObjectifyService.register(Rating.class);
        ObjectifyService.register(Item.class);
        //ObjectifyService.register(Genre.class);
        ObjectifyService.register(User.class);
    }


    Graph graph


    @PostConstruct
    public void loadGraph() {
        Gremlin.load()


        graph = new TinkerGraph()

        Query q = ofy().query(Item.class)
        q.each {
            graph.addVertex(['type': 'Movie', 'movieId': it.movieId, 'title': it.title])
            // def hits = graph.idx(Tokens.T.v)[[genera: it.genera]].iterator()
            // def generaVertex = hits.hasNext() ? hits.next() : graph.addVertex(['type': 'Genera', 'genera': it.genera])
            // graph.addEdge(movieVertex, generaVertex, 'hasGenera')
        }


        q = ofy().query(User.class)
        q.each {
            graph.addVertex(['type': 'User', 'userId': it.userId.toInteger(), 'gender': it.gender, 'age': it.age])

        }


        q = ofy().query(Rating.class)
        q.each {
            def ratedEdge = graph.addEdge(graph.idx(Tokens.T.v)[[userId: it.userId]].next(), graph.idx(Tokens.T.v)[[movieId: it.movieId]].next(), 'rated');
            ratedEdge.setProperty('stars', it.stars)

        }


    }
}
