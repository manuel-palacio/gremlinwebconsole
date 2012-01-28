package org.gremlinconsole.moviegraph

import com.googlecode.objectify.annotation.Cached
import javax.persistence.Id

@Cached
public class User {
    @Id
    Long id;

    int userId;
    String gender;
    int age;
}