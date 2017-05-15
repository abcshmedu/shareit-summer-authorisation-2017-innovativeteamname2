package edu.hm.management.user;

/**
 * Implementation of User Class.
 * @author Daniel Gabl
 *
 */
public class User {
    
    private final String name;
    private final String password;
    private final Role role;
    
    /**
     * Default Constructor for Jackson.
     */
    public User()  {
        this("John Doe", "123456", Role.USER);
    }
    
    /**
     * Another Constructor for Jackson.
     * @param name Name of User
     * @param password Password of User
     */
    public User(String name, String password)  {
        this(name, password, Role.USER);
    }
    
    /**
     * Constructor for Role.
     * @param name Name of User
     * @param password Password of User
     * @param role Role of User
     */
    public User(String name, String password, Role role)  {
        this.name = name;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Returns Name of User.
     * @return user name
     */
    public String getName()  {
        return name;
    }
    
    /**
     * Returns Password of User.
     * @return user password
     */
    public String getPassword()  {
        return password;
    }
    
    /**
     * Returns Role of User.
     * @return user role
     */
    public Role getRole()  {
        return role;
    }
    
    /** Returns if a User has a specific Role.
     * @param role Role to check
     * @return true if user has role
     */
    public boolean hasRole(Role role)  {
        if (this.role.getRoleLevel() <= role.getRoleLevel())  {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)  {
            return true;
        }
        if (obj == null)  {
            return false;
        }
        if (getClass() != obj.getClass())  {
            return false;
        }
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)  {
                return false;
            }
        } else if (!name.equals(other.name))  {
            return false;
        }
        if (password == null) {
            if (other.password != null)  {
                return false;
            }
        } else if (!password.equals(other.password))  {
            return false;
        }
        if (role != other.role)  {
            return false;
        }
        return true;
    }
    
}
