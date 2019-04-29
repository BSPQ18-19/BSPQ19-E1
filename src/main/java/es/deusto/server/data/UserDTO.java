package es.deusto.server.data;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userID;
    private String name;
    private boolean isGuest;

    public UserDTO(String userID, String name, boolean isGuest) {
        this.userID = userID;
        this.name = name;
        this.isGuest = isGuest;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean isGuest) {
        this.isGuest = isGuest;
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
