package com.mcb.demo

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by aacs on 2017. 12. 12..
 */
interface ClubRepo extends JpaRepository<Club, Long> {

}