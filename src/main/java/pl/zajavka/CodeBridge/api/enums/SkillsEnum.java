package pl.zajavka.CodeBridge.api.enums;

public enum SkillsEnum {

    AKKA("Akka"),
    ANGULAR("Angular"),
    BOOTSTRAP("Bootstrap"),
    BROWSER_DEVELOPER_TOOLS("Browser Developer Tools"),
    CSS("CSS"),
    DOCKER("Docker"),
    ENGLISH("English"),
    GERMAN("German"),
    GIT("GIT"),
    GRADLE("Gradle"),
    HIBERNATE("Hibernate"),
    HTML("HTML"),
    JAVA("Java"),
    JAVA_SCRIPT("Java Script"),
    JUNIT("JUnit"),
    J_QUERY("jQuery"),
    KAFKA("Kafka"),
    KOTLIN("Kotlin"),
    LINUX("Linux"),
    MAVEN("Maven"),
    MICROSERVICES("Microservices"),
    NET(".NET"),
    PHP("PHP"),
    PYTHON("Python"),
    REACT("React"),
    REST_API("REST API"),
    RUBY("Ruby"),
    SPRING("Spring"),
    SPRING_BOOT("Spring Boot"),
    TYPE_SCRIPT("TypeScript"),
    VISUAL_STUDIO_CODE("Visual Studio Code"),
    WEB_STORM("WebStorm");


    private final String skill;

    SkillsEnum(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }


}
