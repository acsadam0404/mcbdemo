package com.mcb.demo

import org.springframework.data.annotation.Id

import javax.annotation.Generated
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 * Created by aacs on 2017. 12. 12..
 */
@Entity
@Table(name = "club")
class Club {

    public static final String NAME = "name"
    public static final String COLOUR = "colour"

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected long id

    @NotNull
    String name

    String colour

    @Override
    String toString() {
        return name
    }
}
