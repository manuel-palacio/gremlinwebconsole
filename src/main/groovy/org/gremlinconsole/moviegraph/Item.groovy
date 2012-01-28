package org.gremlinconsole.moviegraph

import com.googlecode.objectify.annotation.Cached
import javax.persistence.Id

@Cached
public class Item {
    @Id
    Long id;

    int movieId;

    String title;       
    
    String genera
}
