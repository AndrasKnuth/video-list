package com.knuth.videolist.services;

import com.knuth.videolist.models.Video;

import java.util.List;
import java.util.Set;

public interface IVideoService {
    List<Video> findAll();
    Set<Video> findAllOnPlaylist();
    void addToPlayList(long id);
    Video findById(long id);
    void save(Video video);
    void delete(long id);
    void edit(Video video);
    boolean validVideo(String name, String length, String description);
}
