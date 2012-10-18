package org.gremlinconsole

import org.gremlinconsole.moviegraph.MovieGraphFactory
import com.tinkerpop.gremlin.groovy.Gremlin
import org.junit.Test

class MovieGraphFactoryTest {


    @Test
    void testBuildGraph() {

        Gremlin.load()

        def g = MovieGraphFactory.getInstance().getGraph()
        assert g.V.count() > 0

    }


    @Test
    void testFilterFile() {
        def newLines = []
        new File("data/u.data").eachLine {
            def components = it.split('\\t')
            if (components[0].toInteger() <= 400 && components[1].toInteger() <= 400) {
                newLines << it
            }

        }

        File newFile = new File("data/u2.data")

        newLines.each {
            newFile << it + System.getProperty("line.separator")
        }
    }

}
