package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class OwnerTest {

    private Owner owner;
    private Set<Pet> pets;

    private Pet pet1 = new Pet();
    private Pet pet2 = new Pet();
    private Pet pet3 = new Pet();
    private Pet pet4 = new Pet();
    private Pet pet5 = new Pet();


    @BeforeEach
    public void setup() {
        owner = new Owner();

        pets = new HashSet<>();
        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
        pets.add(pet4);
    }

    @Test
    public void setGetTelephoneTest() {
        owner.setTelephone("09392324236");
        assertEquals(owner.getTelephone(), "09392324236");
    }

    @Test
    public void setGetCityTest() {
        owner.setCity("Tehran");
        assertEquals(owner.getCity(), "Tehran");
    }

    @Test
    public void setGetAddressTest() {
        owner.setAddress("Tehran, Pelak 255");
        assertEquals(owner.getAddress(), "Tehran, Pelak 255");
    }

    @Test
    public void setGetPetsInternalTest(){
        assertTrue(owner.getPetsInternal().size() == 0); // for empty set of pets

        owner.setPetsInternal(pets);

        assertTrue(owner.getPetsInternal().size() == pets.size() && 
        owner.getPetsInternal().containsAll(pets) && 
        pets.containsAll(owner.getPetsInternal()));

    }

    @Test 
    public void getPetsTest(){
        assertTrue(owner.getPets().size() == 0); // for empty set of pets

        owner.setPetsInternal(pets);

        assertTrue(owner.getPets().size() == pets.size() && 
        owner.getPets().containsAll(pets) && 
        pets.containsAll(owner.getPets()));
    }

    @Test
    public void addPetTest(){
        List<Pet> petsBeforeAdd = owner.getPets();

        owner.addPet(pet5);
        
        assertTrue(owner.getPets().size() == petsBeforeAdd.size() + 1 && 
        owner.getPets().containsAll(petsBeforeAdd) &&
        owner.getPets().contains(pet5));

        owner.addPet(pet5); // add duplicate pet

        assertTrue(owner.getPets().size() == petsBeforeAdd.size() + 1 && 
        owner.getPets().containsAll(petsBeforeAdd) &&
        owner.getPets().contains(pet5));
    }

    @Test
    public void removePetTest(){
        owner.setPetsInternal(pets);
        List<Pet> petsBeforeRemove = owner.getPets();

        owner.removePet(pet4);

        assertTrue(owner.getPets().size() == petsBeforeRemove.size() - 1 &&
        petsBeforeRemove.containsAll(owner.getPets()) &&
        !owner.getPets().contains(pet4));

        owner.removePet(pet4); // remove duplicate pet

        assertTrue(owner.getPets().size() == petsBeforeRemove.size() - 1 &&
        petsBeforeRemove.containsAll(owner.getPets()) &&
        !owner.getPets().contains(pet4));
    }
}
