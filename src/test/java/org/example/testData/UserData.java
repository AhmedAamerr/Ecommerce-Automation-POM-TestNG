package org.example.testData;

public class UserData {
    private static UserData instance;
    private String email;
    private String password;

    private UserData() {
    }

    // Singleton pattern to get the same instance
    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
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
}
