package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Создание нового клиента", description = "Позволяет создать нового клиента в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
    })
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.saveClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @Operation(summary = "Получить список всех клиентов", description = "Возвращает список всех клиентов")
    @ApiResponse(responseCode = "200", description = "Список клиентов успешно возвращен")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Получить клиента по ID", description = "Возвращает клиента по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент найден"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удалить клиента", description = "Удаляет клиента по его ID")
    @ApiResponse(responseCode = "204", description = "Клиент успешно удален")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
