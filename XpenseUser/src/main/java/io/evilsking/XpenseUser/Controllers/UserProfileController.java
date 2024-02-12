package io.evilsking.XpenseUser.Controllers;


import io.evilsking.XpenseUser.Models.UserProfileModel;
import io.evilsking.XpenseUser.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class UserProfileController {


    @Autowired
    private UserService userService;


    @RequestMapping(method= RequestMethod.GET, path="/details/{profileId}")
    public ResponseEntity<Optional<UserProfileModel>> getUserProfile(@PathVariable Long profileId){
        Optional<UserProfileModel> userProfileModel = userService.getUserProfile(profileId);
        return ResponseEntity.ok(userProfileModel);
    }

    @RequestMapping(method= RequestMethod.POST, path="/update/{profileId}")
    public ResponseEntity<UserProfileModel> updateUserProfile(@RequestBody UserProfileModel userProfileModel, @PathVariable Long profileId){
        UserProfileModel updatedUserProfile = userService.updateUserProfile(userProfileModel, profileId);
        return ResponseEntity.ok(updatedUserProfile);
    }


}
