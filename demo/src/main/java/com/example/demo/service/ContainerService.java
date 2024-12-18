package com.example.demo.service;


import com.example.demo.model.Container;
import com.example.demo.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    @Autowired
    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public Container saveContainer(Container container) {
        return containerRepository.save(container);
    }

    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    public Optional<Container> getContainerById(Long id) {
        return containerRepository.findById(id);
    }

    public Container updateContainer(Long id, Container updatedContainer) {
        Container container = containerRepository.findById(id).orElseThrow(() -> new RuntimeException("Container not found"));
        container.setContents(updatedContainer.getContents());
        return containerRepository.save(container);
    }

    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }
}

