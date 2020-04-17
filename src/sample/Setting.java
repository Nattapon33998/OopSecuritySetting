package sample;

import java.io.Serializable;

public class Setting implements Serializable {
    private String password;
    private boolean isLock = false;

    public String getPassword() {
        return password;
    }

    public boolean isLock() {
        return isLock;
    }

    public Setting(String password, boolean isLock) {
        this.password = password;
        this.isLock = isLock;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "password='" + password + '\'' +
                ", isLock=" + isLock +
                '}';
    }
}
