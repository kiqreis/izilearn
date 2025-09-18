package br.com.izilearn.izilearn_application.core.domain.enums;

public enum UserStatus {

    PENDING("Pending"),
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    BANNED("Banned"),
    INACTIVE("Inactive"),
    DELETED("Deleted");

    public final String status;

    UserStatus(String status) {
        this.status = status;
    }

}
