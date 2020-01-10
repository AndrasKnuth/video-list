package com.knuth.videolist.services;

import com.knuth.videolist.models.PopUpFrame;
import com.knuth.videolist.models.Video;
import com.knuth.videolist.repositories.VideoRepository;
import org.springframework.stereotype.Service;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoServiceImp implements IVideoService {
    private VideoRepository videoRepository;

    public VideoServiceImp(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        videoRepository.findAll().forEach(videos::add);
        return videos;
    }

    @Override
    public Set<Video> findAllOnPlaylist() {
        Set<Video> playList = new HashSet<>();
        List<Video> videos = new ArrayList<>();
        videoRepository.findAll().forEach(videos::add);
        for (Video video : videos) {
            if (video.isOnPlayList()) {
                videoRepository.findAll().forEach(playList::add);
            }
        }
        return playList;
    }

    @Override
    public void addToPlayList(long id) {
        videoRepository.findById(id).get().setOnPlayList(true);
    }

    @Override
    public Video findById(long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void delete(long id) {
        //PopUpFrame popUpFrame = new PopUpFrame();
        //popUpFrame.deleteVideo(videoRepository.findById(id).get().getName());
        videoRepository.deleteById(id);
    }

    @Override
    public void edit(Video video) {
        videoRepository.save(video);
    }

    @Override
    public boolean validVideo(String name, String length, String description) {
        return !name.isEmpty() && !length.isEmpty() && !description.isEmpty();
    }


}
