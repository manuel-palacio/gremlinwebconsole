import com.google.appengine.api.datastore.Entity

new File('data/u.user').eachLine {def line ->
    def components = line.split('\\|')
    def entity = new Entity("User")
    entity.type = "User"
    entity.userId = components[0].toInteger()
    entity.gender = components[2]
    entity.age = components[1].toInteger()

    entity.save()
    // graph.addVertex(['type': 'User', 'userId': components[0].toInteger(), 'gender': components[2], 'age': components[1].toInteger()]);
}

