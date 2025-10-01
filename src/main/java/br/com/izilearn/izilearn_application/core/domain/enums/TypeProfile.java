package br.com.izilearn.izilearn_application.core.domain.enums;

public enum TypeProfile {
    STUDENT("Student"),
    TEACHER("Teacher");

    public final String profile;

    TypeProfile(String profile) {
        this.profile = profile;
    }
}
