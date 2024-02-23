package org.example.db;


import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InitDataBase {
    @Autowired
    private CardRepository cardRepository;
  @Autowired
  ProfileRepository profileRepository ;

    public  void adminInit() {

        Profile profile = new Profile();
        profile.setName("Admin");
        profile.setSurname("Adminjon");
        profile.setPhone("123");
        profile.setPassword(MD5Util.encode("123"));
        profile.setCreatedDate(LocalDateTime.now());
        profile.setStatus(GeneralStatus.ACTIVE);
        profile.setRole(ProfileRole.ADMIN);


        Profile profile1 = profileRepository.getProfileByPhone(profile.getPhone());
        if (profile1 != null) {
            return;
        }
        profileRepository.saveProfile(profile);
    }

    public  void addCompanyCard() {
        Card card = new Card();
        card.setCardNumber("5555");
        card.setExpDate(LocalDate.of(2025, 12, 01));

        card.setPhone("123");
        card.setBalance(0d);
        card.setCreatedDate(LocalDateTime.now());
        card.setStatus(GeneralStatus.ACTIVE);


        Card exists = cardRepository.getCardByNumber(card.getCardNumber());

        if (exists != null) {
            return;
        }
        cardRepository.save(card);
    }
}
