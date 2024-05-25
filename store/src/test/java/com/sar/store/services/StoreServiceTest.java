package com.sar.store.services;
import com.sar.store.models.StoreModel;
import com.sar.store.repositories.IStoreRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//Clase de pruebas de implementación de bodegas.
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    private IStoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @Test
    public void testGetStores() {
        // Datos de prueba
        List<StoreModel> storesList = new ArrayList<>();
        storesList.add(new StoreModel());
        storesList.add(new StoreModel());

        // Configuración del comportamiento esperado del mock
        when(storeRepository.findAll()).thenReturn(storesList);

        // Llamada al método que quieres probar
        ArrayList<StoreModel> result = storeService.getStores();

        // Verificación de que el método findAll del repositorio fue invocado
        verify(storeRepository, times(1)).findAll();

        // Verificación de que el resultado del método es el esperado
        assertNotNull(result);
        assertEquals(2, result.size()); // Puedes ajustar esto según el comportamiento esperado
    }

    @Test
    public void testSaveStore() {
        //datos de prueba
        StoreModel storeToSave = new StoreModel(); // Puedes inicializarlo con valores necesarios
        // Configuración del comportamiento esperado del mock
        when(storeRepository.save(any(StoreModel.class))).thenReturn(storeToSave);
        // Llamada al método que quieres probar
        StoreModel savedStore = storeService.saveStore(storeToSave);
        // Verificación de que el resultado del método es el esperado
        assertNotNull(savedStore);
    }

    // Prueba para getStoresByTemporaryStorage
    @Test
    public void getStoresByTemporaryStorage() {
        // Datos de prueba
        Boolean temporaryStorage = true;

        // Configuración del comportamiento esperado del mock
        when(storeRepository.findByTemporaryStorageOrderByCapacityDesc(temporaryStorage)).thenReturn(new ArrayList<>());

        // Llamada al método que quieres probar
        ArrayList<StoreModel> result = storeService.getStoresByTemporaryStorage(temporaryStorage);

        // Verificación de que el método fue invocado con los parámetros correctos
        verify(storeRepository, times(1)).findByTemporaryStorageOrderByCapacityDesc(eq(temporaryStorage));

        // Verificación de que el resultado del método es el esperado
        assertNotNull(result);
        assertEquals(0, result.size()); // Puedes ajustar esto según el comportamiento esperado
    }

    // Prueba para getStoresByType
    @Test
    public void testGetStoresByType() {
        // Datos de prueba
        String typeStoreId = "exampleType";

        // Configuración del comportamiento esperado del mock
        when(storeRepository.findByTypeStoreIdOrderByCapacityDesc(typeStoreId)).thenReturn(new ArrayList<>());

        // Llamada al método que quieres probar
        ArrayList<StoreModel> result = storeService.getStoresByType(typeStoreId);

        // Verificación de que el método fue invocado con los parámetros correctos
        verify(storeRepository, times(1)).findByTypeStoreIdOrderByCapacityDesc(eq(typeStoreId));

        // Verificación de que el resultado del método es el esperado
        assertNotNull(result);
        assertEquals(0, result.size()); // Puedes ajustar esto según el comportamiento esperado
    }

    // Prueba para getById
    @Test
    public void testGetById() {
        // Datos de prueba
        Long id = 1L;
        Optional<StoreModel> storeOptional = Optional.of(new StoreModel());

        // Configuración del comportamiento esperado del mock
        when(storeRepository.findById(id)).thenReturn(storeOptional);

        // Llamada al método que quieres probar
        Optional<StoreModel> result = storeService.getById(id);

        // Verificación de que el método fue invocado con los parámetros correctos
        verify(storeRepository, times(1)).findById(eq(id));

        // Verificación de que el resultado del método es el esperado
        assertNotNull(result);
        assertTrue(result.isPresent());
        // Puedes agregar más aserciones según las propiedades del objeto StoreModel que estás esperando
    }

    // Prueba para updateById
    @Test
    public void testUpdateById() {
        // Datos de prueba
        Long id = 1L;
        StoreModel request = new StoreModel();
        request.setAddress("Nueva dirección");

        StoreModel existingStore = new StoreModel();
        existingStore.setId(id);

        // Configuración del comportamiento esperado del mock
        when(storeRepository.findById(id)).thenReturn(Optional.of(existingStore));
        when(storeRepository.save(any(StoreModel.class))).thenReturn(existingStore);

        // Llamada al método que quieres probar
        StoreModel updatedStore = storeService.updateById(request, id);

        // Verificación de que el método findById y save del repositorio fueron invocados con los parámetros correctos
        verify(storeRepository, times(1)).findById(eq(id));
        verify(storeRepository, times(1)).save(eq(existingStore));

        // Verificación de que el resultado del método es el esperado
        assertNotNull(updatedStore);
        assertEquals("Nueva dirección", updatedStore.getAddress());
        // Puedes agregar más aserciones según las propiedades del objeto StoreModel que estás esperando
    }

    // Prueba para deleteStore
    @Test
    public void testDeleteStore() {
        // Datos de prueba
        Long id = 1L;

        // Configuración del comportamiento esperado del mock
        doNothing().when(storeRepository).deleteById(id);

        // Llamada al método que quieres probar
        Boolean result = storeService.deleteStore(id);

        // Verificación de que el método deleteById del repositorio fue invocado con los parámetros correctos
        verify(storeRepository, times(1)).deleteById(eq(id));

        // Verificación de que el resultado del método es el esperado
        assertTrue(result);
    }
}
