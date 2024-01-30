package com.onetuks.happyparkingserver.util.mock_user;

import com.onetuks.happyparkingserver.auth.model.vo.RoleType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    long memberId() default 1_234_567L;

    String socialId() default "social_id";

    RoleType authorities() default RoleType.USER;

}
