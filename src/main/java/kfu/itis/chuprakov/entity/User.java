package kfu.itis.chuprakov.entity;

public class User {
    private Integer id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String profilePhoto;

    public User() {}

    public User(Integer id, String name, String lastname, String login, String password, String profilePhoto) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPath() {
        return profilePhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}