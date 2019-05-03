package es.deusto.server.jdo;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public abstract class User {

    @PrimaryKey
    private String userID;
    @Persistent(defaultFetchGroup="true")
    private String name;
    @Persistent(defaultFetchGroup="true")
    private String email;
    @Persistent(defaultFetchGroup="true")
    private String password;


    public User(String userID, String name, String email, String password, String address) {
        super();
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String userID, String name) {
        super();
        this.userID = userID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        //Two users are equal objects if and only if they have the same userID.
        if(o instanceof User) {
            final User object = (User) o;
            return object.getUserID().equals(userID);
        }
        return false;
    }

}
