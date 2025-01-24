package pl.zajavka.CodeBridge.api.enums;

public enum StatusEnum {

    LOOKING_FOR_A_JOB("Actively looking for a job"),
    NOT_INTERESTED("Not currently looking for a job"),
    HIRED("Currently employed");

    private final String description;

    StatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
