package com.java.study.condition;

import java.util.List;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

abstract class BaseProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        List<String> activeProfile = List.of(context.getEnvironment().getActiveProfiles());
        List<Profile> targetProfiles = this.getTargetProfile();

        return activeProfile.stream().anyMatch(
                (act) -> targetProfiles.stream().anyMatch(
                        (tgt) -> tgt.isEqualTo(act))
        );
    }

    abstract List<Profile> getTargetProfile();
}
