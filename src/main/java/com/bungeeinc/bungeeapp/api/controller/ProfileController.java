package com.bungeeinc.bungeeapp.api.controller;

import com.bungeeinc.bungeeapp.api.annotation.activeuser.ActiveUser;
import com.bungeeinc.bungeeapp.api.service.ProfileService;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.profile.setprivate.response.SetPrivateResponse;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.profile.show.response.ProfileResponse;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.profile.update.request.UpdateProfileRequest;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.profile.update.response.UpdateProfileResponse;
import com.bungeeinc.bungeeapp.database.models.account.BungeeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/show")
    public ProfileResponse show(@RequestParam(value = "user_id") int id, @ActiveUser BungeeUserDetails userDetails) {
        return profileService.showProfile(id, userDetails);
    }

    @PutMapping("/update")
    public UpdateProfileResponse update(@RequestBody @Valid UpdateProfileRequest request,
                                        @ActiveUser BungeeUserDetails userDetails){
        return profileService.updateProfile(request, userDetails);
    }
    @PutMapping("/set-private")
    public SetPrivateResponse setPrivate (@ActiveUser BungeeUserDetails userDetails) {
        return profileService.setPrivate(userDetails);
    }
}