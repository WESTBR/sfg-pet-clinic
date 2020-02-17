package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.PetTypeServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerServiceMap ownerServiceMap ;
    private final VetServiceMap vetServiceMap ;
    private final PetTypeServiceMap petTypeServiceMap;

    public DataLoader(OwnerServiceMap ownerServiceMap, VetServiceMap vetServiceMap, PetTypeServiceMap petTypeServiceMap) {
        this.ownerServiceMap = ownerServiceMap;
        this.vetServiceMap = vetServiceMap;
        this.petTypeServiceMap = petTypeServiceMap;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeServiceMap.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeServiceMap.save(cat);

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
        ownerServiceMap.save(owner1);

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
        ownerServiceMap.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetServiceMap.save(vet1);

        Vet vet2 = new Vet();
        vet2.setLastName("Jessie");
        vet2.setFirstName("Porter");

        vetServiceMap.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
