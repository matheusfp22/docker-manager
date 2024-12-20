package com.mfp.docker_manager.controllers;

import com.github.dockerjava.api.model.Container;
import com.mfp.docker_manager.services.DockerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/list")
    public List<Container> listContainers(@RequestParam(required = false, defaultValue = "true") Boolean showAll) {
        return dockerService.listContainers(showAll);
    }

    @PostMapping("/{id}/start")
    public void startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
    }

    @PostMapping("/{id}/stop")
    public void stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
    }

    @PostMapping("/{id}/delete")
    public void deleteContainer(@PathVariable String id) {
        dockerService.deleteContainer(id);
    }

    @PostMapping("/create")
    public void createContainer(@PathVariable String imageName) {
        dockerService.createContainer(imageName);
    }

}
