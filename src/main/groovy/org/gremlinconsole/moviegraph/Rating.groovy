package org.gremlinconsole.moviegraph

import com.googlecode.objectify.annotation.Cached;

import javax.persistence.Id;

@Cached
public class Rating {
    @Id
    Long id;

    int userId;
    int movieId;
    int stars;


}
