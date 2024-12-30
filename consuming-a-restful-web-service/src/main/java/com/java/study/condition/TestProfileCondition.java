package com.java.study.condition;

import java.util.List;

public class TestProfileCondition extends BaseProfileCondition {

    @Override
    List getTargetProfile() {
        return List.of(Profile.TEST);
    }
}
