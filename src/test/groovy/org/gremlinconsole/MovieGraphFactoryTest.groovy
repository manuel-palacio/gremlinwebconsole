package org.gremlinconsole

class MovieGraphFactoryTest extends GroovyTestCase {

    void testBuildGraph() {
        MovieGraphFactory movieGraphFactory = new MovieGraphFactory()

        movieGraphFactory.createGraph()
        // assert movieGraphFactory.V.count() > 0

    }

    void testFilterFile() {
        def newLines = []
        new File("data/u.data").eachLine {
            def components = it.split('\\t')
            if (components[0].toInteger() <= 500 && components[1].toInteger() <= 500) {
                newLines << it
            }

        }

        File newFile = new File("data/u2.data")

        newLines.each {
            newFile << it + System.getProperty("line.separator")
        }
    }

}
