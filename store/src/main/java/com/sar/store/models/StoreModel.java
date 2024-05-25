package com.sar.store.models;

import jakarta.persistence.*;

//Entidad de negocio de bodegas.
@Entity
@Table(name="store")
public class StoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameStore;
    private String typeStoreId;
    private String address;
    private String latitude;
    private String longitude;
    private Double capacity;
    private Boolean temporaryStorage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getTypeStoreId() {
        return typeStoreId;
    }

    public void setTypeStoreId(String typeStoreId) {
        this.typeStoreId = typeStoreId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Boolean getTemporaryStorage() {
        return temporaryStorage;
    }

    public void setTemporaryStorage(Boolean temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }
}
