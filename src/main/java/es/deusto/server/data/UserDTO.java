package es.deusto.server.data;

import es.deusto.server.jdo.User;
import es.deusto.server.jdo.UserType;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long userID;
    private String name;
    private UserType type;

    public UserDTO(long userID, String name, boolean isGuest) {
        this.userID = userID;
        this.name = name;
    }
    public UserDTO(User user){
        this.userID = user.getId();
        this.name = user.getName();
        this.type = user.getType();
    }

    public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
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
            return object.getUserID() == userID;
        }
        return false;
    }

}
