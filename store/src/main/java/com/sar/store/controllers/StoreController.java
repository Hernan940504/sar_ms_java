package com.sar.store.controllers;


import com.sar.store.models.StoreModel;
import com.sar.store.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public ArrayList<StoreModel> getStores(){
        return this.storeService.getStores();
    }

    @PostMapping
    public StoreModel saveStore(@RequestBody StoreModel store){
        return this.storeService.saveStore(store);
    }

    @GetMapping(path = "getStoresType/{typeStoreId}")
    public ArrayList<StoreModel> getStoresType(@PathVariable("typeStoreId") String typeStoreId){
        return this.storeService.getStoresByType(typeStoreId);
    }

    @GetMapping(path = "getStoresTemporary/{temporary}")
    public ArrayList<StoreModel> getStoresType(@PathVariable("temporary") Boolean temporary){
        return this.storeService.getStoresByTemporaryStorage(temporary);
    }

    @GetMapping(path = "{id}")
    public Optional<StoreModel> getStoreById(@PathVariable("id") Long id){
        return  this.storeService.getById(id);
    }

    @PutMapping(path = "{id}")
    public StoreModel updateStoreById(@RequestBody StoreModel request, @PathVariable("id") Long id){
        return this.storeService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok =  this.storeService.deleteStore(id);
        if(ok){
            return "Bodega con Id " + id + " eliminada!!";
        } else {
            return "Error, tenemos un problema al intentar eliminar la bodega " + id;
        }
    }
}
