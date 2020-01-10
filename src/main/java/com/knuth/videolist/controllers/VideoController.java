package com.knuth.videolist.controllers;

import com.knuth.videolist.models.Video;
import com.knuth.videolist.services.VideoServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class VideoController {
    VideoServiceImp videoServiceImp;

    public VideoController(VideoServiceImp videoServiceImp) {
        this.videoServiceImp = videoServiceImp;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/videolist")
    public String videoList(Model model) {
        model.addAttribute("videoList", videoServiceImp.findAll());
        return "videolist";
    }

    /*@GetMapping("/addtoplaylist")
    public String addItemForm(Model model) {
        model.addAttribute("playList", new HashSet<Video>() {
        });
        model.addAttribute("videoList", videoServiceImp.findAll());
        return "form";
    }

    @PostMapping("/addtoplaylist")
    public String addItem(@ModelAttribute("playList") HashSet<Video> playList) {
        playList.add() .getSeatingList().forEach(s -> System.out.println(s.getSeatNo()));
        return "redirect:/addItem";
    }*/

    @GetMapping("/playlist")
    public String playList(Model model) {
        model.addAttribute("playList", videoServiceImp.findAllOnPlaylist());
        return "playlist";
    }

    @PostMapping("/addtoplaylist")
    public String addToPlaylist(Video video) {
        videoServiceImp.addToPlayList(video.getId());
        return "redirect:/playlist";
    }

    @GetMapping("/addvideo")
    public String getVideo(Model model) {
        model.addAttribute("videoList", videoServiceImp.findAll());
        return "addvideo";
    }

    @PostMapping("/addvideo")
    public String postVideo(String name, String length, String description) {
        if (videoServiceImp.validVideo(name, length, description)) {
            videoServiceImp.save(new Video(name, length, description));
            return "redirect:/videolist";
        }
        else return "redirect:/addvideo";
    }

    @RequestMapping("/video/{id}/delete")
    public String removeVideoById(Model model, @PathVariable("id") long id) {
        videoServiceImp.delete(id);
        model.addAttribute("videoList", videoServiceImp.findAll());
        return "redirect:/videolist";
    }

    @GetMapping("/{id}/edit")
    public String editVideoById(Model model, @PathVariable("id") long id) {
        model.addAttribute("video", videoServiceImp.findById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editAndSaveVideo(@PathVariable long id, Video video) {
        Video oldVideo = videoServiceImp.findById(id);
        oldVideo.setName(video.getName());
        oldVideo.setLength(video.getLength());
        oldVideo.setDescription(video.getDescription());

        videoServiceImp.edit(oldVideo);
        return "redirect:/videolist";
    }
}
