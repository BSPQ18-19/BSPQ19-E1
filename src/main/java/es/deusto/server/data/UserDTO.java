package es.deusto.server.data;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userID;
    private String name;

    public UserDTO(String userID, String name, boolean isGuest) {
        this.userID = userID;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof UserDTO) {
            UserDTO object = (UserDTO) o;
            return object.getUserID().equals(userID);
        }
        return false;
    }

}
