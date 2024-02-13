package io.evilsking.XpenseUser.Repositories;

import io.evilsking.XpenseUser.Models.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long> {

//    UserProfileModel findByUserId(Long userId);

    Optional<UserProfileModel> findByProfileId(Long profileId);

}
