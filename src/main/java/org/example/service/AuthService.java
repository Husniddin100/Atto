package org.example.service;


import org.example.controller.AdminController;
import org.example.controller.ProfileController;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.ProfileRepository;
import org.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AuthService {
    //
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private ProfileController profileController;


    public void login(String phone, String password) {
        Profile profile = profileRepository.getProfileByPhoneAndPassword(phone, MD5Util.encode(password));

        if (profile == null) {
            System.out.println("Phone or Password incorrect");
            return;
        }

        if (!profile.getStatus().equals(GeneralStatus.ACTIVE)) {
            System.out.println("You not allowed.MF");
            return;
        }

        if (profile.getRole().equals(ProfileRole.ADMIN)) {

            adminController.start(profile);
        } else if (profile.getRole().equals(ProfileRole.USER)) {

            profileController.start(profile);
        } else {
            System.out.println("You don't have any role.");
        }

    }

    public void registration(Profile profile) {
        // check
        Boolean exist = profileRepository.isPhoneExist(profile.getPhone()); // unique
        if (exist) {
            System.out.println(" Phone already exist.");
            return;
        }

        profile.setStatus(GeneralStatus.ACTIVE);
        profile.setCreatedDate(LocalDateTime.now());
        profile.setRole(ProfileRole.USER);
        profile.setPassword(MD5Util.encode(profile.getPassword()));
        int result = profileRepository.saveProfile(profile);

        if (result != 0) {
            System.out.println("Profile created.");
        }
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}
