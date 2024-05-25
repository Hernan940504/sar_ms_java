package com.sar.store.services;

import com.sar.store.models.StoreModel;
import com.sar.store.repositories.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    IStoreRepository storeRepository;

    public ArrayList<StoreModel> getStores(){
        return (ArrayList<StoreModel>) storeRepository.findAll();
    }

    //Metodo para listar las bodegas por tipo
    public ArrayList<StoreModel> getStoresByType(String typeStoreId) {
        return storeRepository.findByTypeStoreIdOrderByCapacityDesc(typeStoreId);
    }

    //Metodo para listar las bodegas temporales.
    public ArrayList<StoreModel> getStoresByTemporaryStorage(boolean temporaryStorage) {
        return storeRepository.findByTemporaryStorageOrderByCapacityDesc(temporaryStorage);
    }

    // Metodo para guardar las bodegas.
    public StoreModel saveStore(StoreModel store){
        return storeRepository.save(store);
    }

    public Optional<StoreModel> getById(Long id){
        return storeRepository.findById(id);
    }

    // Metodo para actualizar las bodegas.
    public StoreModel updateById(StoreModel request, Long id){
        StoreModel store = storeRepository.findById(id).get();
        store.setAddress(request.getAddress());
        store.setNameStore(request.getNameStore());
        store.setLatitude(request.getLatitude());
        store.setLongitude(request.getLongitude());
        store.setTypeStoreId(request.getTypeStoreId());
        store.setCapacity(request.getCapacity());
        store = storeRepository.save(store);
        return store;
    }

    public Boolean deleteStore(Long id){
        try{
            storeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
