package pl.dicedev.kakebo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("asset/")
@Slf4j
public class AssetController {

    private String asset;

    @PostMapping("set")
    public void setInitAsset(@RequestBody String asset) {
        log.info("add in asset controller {}", asset);
        this.asset = asset;
    }

    @GetMapping("get")
    public String getInitAsset() {
        return asset;
    }

}
