package com.example.demo.serviceimp;

import com.example.demo.entity.Parcel;
import com.example.demo.repositories.ParcelRepository;
import com.example.demo.service.ParcelService;
import org.springframework.stereotype.Service;

@Service
public class ParcelServiceImp implements ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelServiceImp(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Parcel addParcel(Parcel parcel) {
        if (parcelRepository.existsByTrackingNumber(parcel.getTrackingNumber())) {
            throw new RuntimeException("tracking exists");
        }
        if (parcel.getWeightKg() <= 0) {
            throw new RuntimeException("weight error");
        }
        return parcelRepository.save(parcel);
    }

    public Parcel getByTrackingNumber(String trackingNumber) {
        return parcelRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
    }
}
