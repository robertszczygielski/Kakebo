package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("asset/")
@AllArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping("set")
    public UUID setInitAsset(@RequestBody AssetDto asset) {
        return assetService.save(asset);
    }

    @GetMapping("get/{id}")
    public AssetDto getInitAsset(@PathVariable("id") UUID id) {
        return assetService.findById(id);
    }

    @GetMapping("get")
    public List<AssetDto> getAll() {
        return assetService.findAll();
    }

}
