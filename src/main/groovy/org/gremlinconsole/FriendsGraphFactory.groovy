package org.gremlinconsole

import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph
import com.tinkerpop.blueprints.pgm.Graph
import com.tinkerpop.gremlin.groovy.Gremlin


class FriendsGraphFactory {

    static TinkerGraph createFriendsGraph() {
        Gremlin.load()

        Graph graph = new TinkerGraph()

        graph.addVertex(1, ['type': 'Friend', 'friendId': 1])
        graph.addVertex(2, ['type': 'Friend', 'friendId': 2])
        graph.addVertex(3, ['type': 'Friend', 'friendId': 3])
        graph.addVertex(4, ['type': 'Friend', 'friendId': 4])
        graph.addVertex(5, ['type': 'Friend', 'friendId': 5])
        graph.addVertex(6, ['type': 'Friend', 'friendId': 6])
        graph.addVertex(7, ['type': 'Friend', 'friendId': 7])
        graph.addVertex(8, ['type': 'Friend', 'friendId': 8])

        graph.addEdge(graph.v(1), graph.v(2), "friend")
        graph.addEdge(graph.v(2), graph.v(3), "friend")
        graph.addEdge(graph.v(3), graph.v(4), "friend")
        graph.addEdge(graph.v(4), graph.v(5), "friend")
        graph.addEdge(graph.v(5), graph.v(6), "friend")
        graph.addEdge(graph.v(6), graph.v(7), "friend")
        graph.addEdge(graph.v(7), graph.v(8), "friend")
        graph.addEdge(graph.v(8), graph.v(1), "friend")

        graph.addEdge(graph.v(1), graph.v(6), "friend")
        graph.addEdge(graph.v(2), graph.v(6), "friend")
        graph.addEdge(graph.v(7), graph.v(1), "friend")
        graph.addEdge(graph.v(5), graph.v(3), "friend")
        return graph
    }

}
