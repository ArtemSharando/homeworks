package ua.dnipro.epam.homework.entity;

public enum RoleName {
    USER("user"), ADMIN("admin");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
