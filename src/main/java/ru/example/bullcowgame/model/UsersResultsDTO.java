package ru.example.bullcowgame.model;

import java.util.Objects;

public class UsersResultsDTO {
    private String username;
    private String attempts;

    public UsersResultsDTO(String username, String attempts) {
        this.username = username;
        this.attempts = attempts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersResultsDTO)) return false;
        UsersResultsDTO that = (UsersResultsDTO) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
