package org.gremlinconsole.moviegraph

import com.googlecode.objectify.annotation.Cached
import javax.persistence.Id

@Cached

public class Genre {
    @Id
    Long id;

    String type
    String genreId
}
