package com.mfp.docker_manager.controllers;

import com.github.dockerjava.api.model.Image;
import com.mfp.docker_manager.services.DockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class DockerImagesController {

    private final DockerService dockerService;

    public DockerImagesController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/list")
    public List<Image> listImages() {
        return dockerService.listImages();
    }

    @GetMapping("/filter")
    public List<Image> filterImages(@RequestParam String imageName) {
        return dockerService.filterImages(imageName);
    }

}
