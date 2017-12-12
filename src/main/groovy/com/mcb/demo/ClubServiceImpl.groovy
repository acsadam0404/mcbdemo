package com.mcb.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by aacs on 2017. 12. 12..
 */
@Service
class ClubServiceImpl implements ClubService {

    private ClubRepo clubRepo

    @Autowired
    ClubServiceImpl(ClubRepo clubRepo) {
        this.clubRepo = clubRepo
    }

    @Override
    List<Club> findAll() {
        return clubRepo.findAll()
    }

    @Override
    void save(Club club) {
        clubRepo.save(club)
    }
}
