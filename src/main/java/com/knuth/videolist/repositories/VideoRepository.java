package com.knuth.videolist.repositories;

import com.knuth.videolist.models.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
}
