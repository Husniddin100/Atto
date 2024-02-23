package org.example.service;


import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public void profileList() {
        LinkedList<Profile> profileList = profileRepository.getProfileList();

        for (Profile profile : profileList) {
            System.out.println(profile);
        }
    }

    public void changeProfileStatus(String phone) {
        Profile profile = profileRepository.getProfileByPhone(phone);
        if (profile == null) {
            System.out.println("Profile not found");
            return;
        }

        if (profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            profileRepository.changeProfileStatus(phone, GeneralStatus.BLOCK);
        } else {
            profileRepository.changeProfileStatus(phone, GeneralStatus.ACTIVE);
        }
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
