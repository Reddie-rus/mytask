package com.example.demo.controller;

import com.example.demo.model.Container;
import com.example.demo.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
public class    ContainerController {

    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @Operation(summary = "Создание нового контейнера", description = "Позволяет создать новый контейнер в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контейнер успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping
    public ResponseEntity<Container> createContainer(@RequestBody Container container) {
        return ResponseEntity.ok(containerService.saveContainer(container));
    }

    @Operation(summary = "Получить список всех контейнеров", description = "Возвращает список всех контейнеров")
    @ApiResponse(responseCode = "200", description = "Список контейнеров успешно возвращен")
    @GetMapping
    public List<Container> getAllContainers() {
        return containerService.getAllContainers();
    }

    @Operation(summary = "Получить контейнер по ID", description = "Возвращает контейнер по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контейнер найден"),
            @ApiResponse(responseCode = "404", description = "Контейнер не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Container> getContainerById(@PathVariable Long id) {
        return containerService.getContainerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновить информацию о контейнере", description = "Обновляет данные контейнера по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контейнер успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Контейнер не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Container> updateContainer(@PathVariable Long id, @RequestBody Container updatedContainer) {
        return ResponseEntity.ok(containerService.updateContainer(id, updatedContainer));
    }

    @Operation(summary = "Удалить контейнер", description = "Удаляет контейнер по его ID")
    @ApiResponse(responseCode = "204", description = "Контейнер успешно удален")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }
}
