package com.onetuks.happyparkingserver.auth;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "members", uniqueConstraints = @UniqueConstraint(columnNames = {"social_id", "provider"}))
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(value = STRING)
    @Column(name = "provider", nullable = false)
    private OAuth2Provider oAuth2Provider;

    @Enumerated(value = STRING)
    @Column(name = "role", nullable = false)
    private RoleType roleType;

    private Member(
            String nickname,
            String socialId,
            OAuth2Provider oAuth2Provider,
            RoleType roleType
    ) {
        this.nickname = nickname;
        this.socialId = socialId;
        this.oAuth2Provider = oAuth2Provider;
        this.roleType = roleType;
    }

    public static Member of(
            String nickname,
            String socialId,
            OAuth2Provider oAuth2Provider,
            RoleType roleType
    ) {
        return new Member(nickname, socialId, oAuth2Provider, roleType);
    }

}