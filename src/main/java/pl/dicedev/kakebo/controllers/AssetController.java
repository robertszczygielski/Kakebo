package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.enums.AssetCategory;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("asset")
@AllArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public UUID setAsset(@RequestBody AssetDto asset) {
        return assetService.save(asset);
    }

    @GetMapping("/{id}")
    public AssetDto getAssetById(@PathVariable("id") UUID id) {
        return assetService.findById(id);
    }

    @GetMapping
    public List<AssetDto> getAll() {
        return assetService.findAll();
    }

    @GetMapping("/categories")
    public List<AssetCategory> getCategories() {
        return asList(AssetCategory.values());
    }

    @GetMapping("/find")
    public List<AssetDto> getExpensesByCategory(@PathParam("category") String category) {
        return null;
    }
}
