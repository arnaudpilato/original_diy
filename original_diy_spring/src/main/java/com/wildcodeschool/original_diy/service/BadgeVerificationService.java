package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeVerificationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    public void badgesVerification() {
        for (DiyUser user: userRepository.findAll()) {
            for (DiyBadge badge: badgeRepository.findAll()) {
                if (!user.getBadges().contains(badge) && workshopRepository.getBadgesByReservations(user.getId()).size() >= badge.getStep() && badge.getStep() > 0) {
                    user.getBadges().add(badge);
                    userRepository.save(user);
                }
            }
        }
    }
}
