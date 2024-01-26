package com.onetuks.happyparkingserver.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialIdAndClientProvider(String socialId, ClientProvider clientProvider);

}
