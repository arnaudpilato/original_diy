package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.BadgeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BadgeService {
    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    public List<DiyBadge> getAllBadges(String searchBadge) {
            List<DiyBadge> badges = new ArrayList<>();

            if (!Objects.equals(searchBadge, "")) {
                badgeRepository.getBadgesByFilter(searchBadge).forEach(badges::add);
            } else {
                badgeRepository.findAll().forEach(badges::add);
            }

            return badges;
    }

    public DiyBadge newBadge(BadgeRequest badgeRequest) {
        DiyBadge badge = new DiyBadge();

        badge.setName(badgeRequest.getName());
        badge.setPicturePath(badgeRequest.getPicturePath());
        badge.setDescription(badgeRequest.getDescription());

        if (badgeRequest.getCondition().equals("manual")) {
            badge.setStep(0);
        } else {
            badge.setStep(badgeRequest.getStep());
        }

        badgeRepository.save(badge);

        return badge;
    }

    public Optional<DiyBadge> getBadgeById(long id) {
        Optional<DiyBadge> badge = badgeRepository.findById(id);

        return badge;
    }

    public Optional<DiyBadge> updateBadge(long id, BadgeRequest badgeRequest) {
        Optional<DiyBadge> badgeData = badgeRepository.findById(id);

        if (badgeData.isPresent()) {
            DiyBadge badge = badgeData.get();
            badge.setName(badgeRequest.getName());

            if (badgeRequest.getPicturePath() == null) {
                badge.setPicturePath(badge.getPicturePath());
            } else {
                badge.setPicturePath(badgeRequest.getPicturePath());
            }

            if (badgeRequest.getCondition().equals("manual")) {
                badge.setStep(0);
            } else {
                badge.setStep(badgeRequest.getStep());
            }

            badge.setDescription(badgeRequest.getDescription());

            badgeRepository.save(badge);
        }

        return badgeData;
    }

    public void deleteBadge(Long id) {
        try {
            badgeRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

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
