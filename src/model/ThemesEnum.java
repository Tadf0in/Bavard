package model;

public enum ThemesEnum {
    INFORMATION("Information"),
    SPORT("Sport"),
    CINEMA("Cinéma"),
    AUTRE("Autre");

    private final String label;

    ThemesEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
