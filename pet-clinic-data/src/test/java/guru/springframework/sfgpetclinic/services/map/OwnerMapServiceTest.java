package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService ;
    final Long id = 1L;
    final Long id2 = 2L;
    final String lastName2 = "Sanang";
    final String lastName = "Ngongang";
    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService() , new PetMapService());
        Owner owner = Owner.builder().city("Aachen").build();
        Owner owner2 = Owner.builder().city("Bagangte").build();
        owner2.setId(id2);
        owner2.setLastName(lastName2);
        owner.setId(id);
        owner.setLastName(lastName);
        ownerMapService.save(owner);
        ownerMapService.save(owner2);
    }

    @Test
    void findAllFalseNumber() {
        Set<Owner> owners = ownerMapService.findAll();
        int notExpectedSize = 1;
        assertNotEquals(notExpectedSize , owners.size());
    }
    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        int expectedSize = 2 ;
        assertEquals(expectedSize , ownerSet.size());
    }
    @Test
    void findById() {
        Owner owner = ownerMapService.findById(id);
        assertEquals(id , owner.getId());
    }

    @Test
    void findNotExistingId() {
        Long notExistingId = 3L;
        Owner owner = ownerMapService.findById(notExistingId);
        assertNull(owner);
    }
    @Test
    void notSameLastNameId() {
        Owner owner = ownerMapService.findById(id);
        Owner owner2 = ownerMapService.findById(id2);
        assertNotEquals(owner2.getLastName() , owner.getLastName());
    }

    @Test
    void save() {
        Owner owner2Save = Owner.builder().city("Dschang").build();
        owner2Save.setId(3L);
        Owner savedOwner = ownerMapService.save(owner2Save);
        assertNotNull(savedOwner);
        assertEquals(3L,savedOwner.getId());
    }

    @Test
    void delete() {
        int expectedCount = 1;
        ownerMapService.delete(ownerMapService.findById(id));
        assertEquals(expectedCount, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
         ownerMapService.deleteById(id2);
         assertNull(ownerMapService.findById(id2));
    }

    @Test
    void findByLastName() {
        String expectedName = "Ngongang";
        Owner owner = ownerMapService.findById(id);
        assertNotNull(owner);
        assertEquals(expectedName , owner.getLastName());
    }
}