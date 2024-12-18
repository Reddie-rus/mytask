package com.example.demo.controller;

import com.example.demo.model.Good;
import com.example.demo.service.GoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {

    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @Operation(summary = "Создание нового товара", description = "Позволяет создать новый товар в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping
    public ResponseEntity<Good> createGood(@RequestBody Good good) {
        return ResponseEntity.ok(goodService.saveGood(good));
    }

    @Operation(summary = "Получить список всех товаров", description = "Возвращает список всех товаров")
    @ApiResponse(responseCode = "200", description = "Список товаров успешно возвращен")
    @GetMapping
    public List<Good> getAllGoods() {
        return goodService.getAllGoods();
    }

    @Operation(summary = "Получить товар по ID", description = "Возвращает товар по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар найден"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Good> getGoodById(@PathVariable Long id) {
        return goodService.getGoodById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновить информацию о товаре", description = "Обновляет данные товара по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Good> updateGood(@PathVariable Long id, @RequestBody Good updatedGood) {
        return ResponseEntity.ok(goodService.updateGood(id, updatedGood));
    }

    @Operation(summary = "Удалить товар", description = "Удаляет товар по его ID")
    @ApiResponse(responseCode = "204", description = "Товар успешно удален")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGood(@PathVariable Long id) {
        goodService.deleteGood(id);
        return ResponseEntity.noContent().build();
    }
}
