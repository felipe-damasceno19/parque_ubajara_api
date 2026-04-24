package io.github.parqueubajara.api.controller;

import io.github.parqueubajara.api.dto.request.TouristSpotRequestDTO;
import io.github.parqueubajara.api.dto.response.TouristSpotResponseDTO;
import io.github.parqueubajara.api.dto.update.TouristSpotUpdateDTO;
import io.github.parqueubajara.api.mapper.TouristSpotMapper;
import io.github.parqueubajara.api.model.TouristSpot;
import io.github.parqueubajara.api.service.TouristSpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tourist-spots")
@RequiredArgsConstructor
public class TouristSpotController {

    private final TouristSpotService service;
    private final TouristSpotMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<TouristSpotResponseDTO> getById(@PathVariable UUID id){
        TouristSpot spot = service.findById(id);
        return ResponseEntity.ok(mapper.toResponseDTO(spot));
    }

    @GetMapping
    public ResponseEntity<Page<TouristSpotResponseDTO>> getAll(
            @PageableDefault(size = 10, sort = "name")Pageable pageable) {
        Page<TouristSpot> pageEntity = service.findAll(pageable);
        Page<TouristSpotResponseDTO> pageDTO = pageEntity.map(mapper::toResponseDTO);
        return ResponseEntity.ok(pageDTO);
    }

    @PostMapping
    public ResponseEntity<TouristSpotResponseDTO> save(
            @RequestBody @Valid TouristSpotRequestDTO requestDTO){
        TouristSpot spot = mapper.toEntity(requestDTO);
        service.save(spot);
        TouristSpotResponseDTO responseDTO = mapper.toResponseDTO(spot);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody TouristSpotUpdateDTO updateDTO){
        TouristSpot spot = service.findById(id);
        service.update(id, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
