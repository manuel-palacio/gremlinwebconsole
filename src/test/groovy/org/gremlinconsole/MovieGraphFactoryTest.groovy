package org.gremlinconsole

class MovieGraphFactoryTest extends GroovyTestCase {

    void testBuildGraph() {
        def g = MovieGraphFactory.createGraph()
        assert g.V.count() > 0

    }
}
