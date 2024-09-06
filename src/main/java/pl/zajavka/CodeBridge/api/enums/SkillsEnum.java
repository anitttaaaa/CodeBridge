package pl.zajavka.CodeBridge.api.enums;

public enum SkillsEnum {

    JAVA("Java"),
    PYTHON("Python"),
    VISUAL_STUDIO_CODE("Visual Studio Code"),
    WEB_STORM("WebStorm"),
    BROWSER_DEVELOPER_TOOLS("Browser Developer Tools"),
    JAVA_SCRIPT("Java Script"),
    J_QUERY("jQuery"),
    HTML("HTML"),
    CSS("CSS"),
    SPRING("Spring"),
    HIBERNATE("Hibernate"),
    REST_API("REST API"),
    ENGLISH("English"),
    GERMAN("German"),
    GRADLE("Gradle"),
    MAVEN("Maven"),
    GIT("GIT"),
    DOCKER("Docker"),
    REACT("React"),
    PHP("PHP"),
    RUBY("Ruby"),
    KOTLIN("Kotlin"),
    TYPE_SCRIPT("TypeScript"),
    ANGULAR("Angular"),
    BOOTSTRAP("Bootstrap");


    private final String skill;

    SkillsEnum(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }


}
