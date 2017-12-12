package com.mcb.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by aacs on 2017. 12. 12..
 */
@RestController
@RequestMapping("/rest")
class ClubRestController {
    private ClubService clubService

    @Autowired
    ClubRestController(ClubService clubService) {
        this.clubService = clubService
    }

    @RequestMapping(value = "/clubs", method = RequestMethod.GET)
    List<Club> findAll() {
        return clubService.findAll()
    }
}
