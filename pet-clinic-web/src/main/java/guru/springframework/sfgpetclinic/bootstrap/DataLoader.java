package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerMapService ownerMapService;
    private final VetMapService vetMapService;
    private final PetTypeMapService petTypeMapService;
    private final SpecialtyService specialtyService ;

    public DataLoader(OwnerMapService ownerMapService, VetMapService vetMapService, PetTypeMapService petTypeMapService, SpecialtyService specialtyService) {
        this.ownerMapService = ownerMapService;
        this.vetMapService = vetMapService;
        this.petTypeMapService = petTypeMapService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeMapService.findAll().size();
        if(count==0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeMapService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeMapService.save(cat);

        System.out.println("Loaded PetType...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("12312414132");

        Pet mikeDog = new Pet();
        mikeDog.setPetType(savedDogPetType);
        mikeDog.setOwner(owner1);
        mikeDog.setBirthDate(LocalDateTime.now());
        mikeDog.setName("Rosco");

        owner1.getPets().add(mikeDog);
        ownerMapService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenane");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("12312414132");

        Pet digiCat = new Pet();
        digiCat.setPetType(savedCatPetType);
        digiCat.setOwner(owner2);
        digiCat.setBirthDate(LocalDateTime.now());
        digiCat.setName("Just Cat");
        owner2.getPets().add(digiCat);
        ownerMapService.save(owner2);

        System.out.println("Loaded Owners...");

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("radiology");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("radiology");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedDentistry);
        vet1.getSpecialties().add(savedSurgery);

        vetMapService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setLastName("Jessie");
        vet2.setFirstName("Porter");
        vet2.getSpecialties().add(savedRadiology);

        vetMapService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
