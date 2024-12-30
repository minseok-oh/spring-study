package com.java.study.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotTestProfileCondition implements Condition {
    private final Condition condition = new TestProfileCondition();

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !condition.matches(context, metadata);
    }
}
