package com.knuth.videolist.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String length;
    private String description;
    private boolean onPlayList;

    public Video() {
    }

    public Video(String name, String length, String description) {
        this.name = name;
        this.length = length;
        this.description = description;
        this.onPlayList = false;
    }



}
