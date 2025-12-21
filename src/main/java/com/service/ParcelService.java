package com.example.demo.service;

import com.example.demo.entity.Parcel;

public interface ParcelService {

    Parcel addParcel(Parcel parcel);

    Parcel getByTrackingNumber(String trackingNumber);
}
