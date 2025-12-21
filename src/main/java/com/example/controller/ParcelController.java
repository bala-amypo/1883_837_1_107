package com.example.demo.controller;

import com.example.demo.entity.Parcel;
import com.example.demo.service.ParcelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public Parcel add(@RequestBody Parcel parcel) {
        return parcelService.addParcel(parcel);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Parcel get(@PathVariable String trackingNumber) {
        return parcelService.getByTrackingNumber(trackingNumber);
    }
}
