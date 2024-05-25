package com.sar.store.repositories;

import com.sar.store.models.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//Interface de bodegas.
@Repository
public interface IStoreRepository extends JpaRepository<StoreModel, Long> {
    ArrayList<StoreModel> findByTypeStoreIdOrderByCapacityDesc(String typeStoreId);

    ArrayList<StoreModel> findByTemporaryStorageOrderByCapacityDesc(boolean temporaryStorage);
}
