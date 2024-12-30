package com.java.study.condition;

public enum Profile {
    DEV("dev"),
    TEST("test"),
    PROD("prod");

    private String profile;

    Profile(String profile) {
        this.profile = profile;
    }

    boolean isEqualTo(String profile) {
        return this.profile.equals(profile);
    }
}
