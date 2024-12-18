package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Создание нового склада", description = "Позволяет создать новый склад в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Склад успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.saveWarehouse(warehouse));
    }

    @Operation(summary = "Получить список всех складов", description = "Возвращает список всех складов")
    @ApiResponse(responseCode = "200", description = "Список складов успешно возвращен")
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @Operation(summary = "Получить склад по ID", description = "Возвращает склад по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Склад найден"),
            @ApiResponse(responseCode = "404", description = "Склад не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновить информацию о складе", description = "Обновляет данные склада по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Склад успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Склад не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse updatedWarehouse) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, updatedWarehouse));
    }

    @Operation(summary = "Удалить склад", description = "Удаляет склад по его ID")
    @ApiResponse(responseCode = "204", description = "Склад успешно удален")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
