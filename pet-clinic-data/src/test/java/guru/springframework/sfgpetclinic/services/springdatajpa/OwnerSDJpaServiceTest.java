package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository ;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService ;

    final String lastName = "Ngongang";
    final Long id = 1L ;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Owner owner = Owner.builder().build();
        owner.setId(id);
        owner.setLastName(lastName);
        when( ownerRepository.findByLastName(any())).thenReturn(owner);
        String expectedLastName = lastName ;
        Owner ngongang = ownerSDJpaService.findByLastName(lastName);
        assertEquals(expectedLastName, ngongang.getLastName());
        verify(ownerRepository).findByLastName(lastName);
    }

    @Test
    void findAllNoElementSave() {
        Set<Owner> ownerSet = new HashSet<>();
        int expectedSize = 0 ;
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        assertEquals(expectedSize, ownerSDJpaService.findAll().size());
    }

    @Test
    void findAllOneElementSave() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().city("Aachen").build());
        int expectedSize = 1 ;
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        assertEquals(expectedSize, ownerSDJpaService.findAll().size());
    }

    @Test
    void findById() {
        Long expectedId = id;
        Owner owner = Owner.builder().city("Bamenda").build();
        owner.setId(1L);
        Optional<Owner> ownerOptional = Optional.of(owner);
        when(ownerRepository.findById(any())).thenReturn(ownerOptional);
        assertNotNull(ownerSDJpaService.findById(any()));
        assertEquals(expectedId , ownerSDJpaService.findById(any()).getId());
    }
    @Test
    void findNotExisting() {
        Long noExistingId = 2l;
        Owner owner = Owner.builder().city("Bamenda").build();
        owner.setId(id);
        Optional<Owner> ownerOptional = Optional.of(owner);
        when(ownerRepository.findById(id)).thenReturn(ownerOptional);
        assertNotNull(ownerSDJpaService.findById(id));
        assertNotEquals(noExistingId , ownerSDJpaService.findById(id).getId());
    }

    @Test
    void save() {
        String savedFirstName ="Danyls";
        Owner owner = Owner.builder().build();
        owner.setId(id);
        owner.setFirstName(savedFirstName);
        when(ownerRepository.save(any())).thenReturn(owner);
        assertNull(ownerSDJpaService.save(owner).getLastName());
        assertNotEquals(2L, ownerSDJpaService.save(owner).getId());
        assertEquals(savedFirstName , ownerSDJpaService.save(owner).getFirstName());
    }

    @Test
    void delete() {
        Owner owner = Owner.builder().build();
        owner.setId(id);
        ownerSDJpaService.delete(owner);
        verify(ownerRepository).delete(owner);
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(id);
        verify(ownerRepository).deleteById(id);
    }
}