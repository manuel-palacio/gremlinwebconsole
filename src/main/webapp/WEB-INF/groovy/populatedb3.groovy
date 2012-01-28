import com.google.appengine.api.datastore.Entity
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Objectify
import org.gremlinconsole.moviegraph.Item
import org.gremlinconsole.moviegraph.User
import org.gremlinconsole.moviegraph.Rating
import com.googlecode.objectify.Query
import com.google.appengine.api.datastore.Cursor
import com.google.appengine.api.taskqueue.QueueFactory


long LIMIT_MILLIS = 1000 * 25 // provide a little leeway


Objectify ofy = ObjectifyService.begin();

long startTime = System.currentTimeMillis();

new File('data/u2.data').eachLine {def line ->
   def components = line.split('\\t')
    def entity = new Entity("Rating")
    entity.userId = components[0].toInteger()
    entity.movieId = components[1].toInteger()
    entity.stars = components[2].toInteger()
    entity.asyncSave()


    if (System.currentTimeMillis() - startTime > LIMIT_MILLIS) {
             Queue queue = QueueFactory.getDefaultQueue();
             queue.add(url("/pathToThisServlet").param("cursor", cursor.toWebSafeString()));
         }



    // def ratedEdge = graph.addEdge(graph.idx(Tokens.T.v)[[userId: components[0].toInteger()]].next(), graph.idx(Tokens.T.v)[[movieId: components[1].toInteger()]].next(), 'rated');
    // ratedEdge.setProperty('stars', components[2].toInteger());
}

