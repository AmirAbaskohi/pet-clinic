package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class OwnerTest {

    private Owner owner;
    private Set<Pet> pets;

    private final Pet pet1 = new Pet();
    private final Pet pet2 = new Pet();
    private final Pet pet3 = new Pet();
    private final Pet pet4 = new Pet();
    private final Pet pet5 = new Pet();

    @BeforeEach
    public void setup() {
        owner = new Owner();

        pets = new HashSet<>();

        pet1.setName("Pet1");
        pet2.setName("Pet2");
        pet3.setName("Pet3");
        pet4.setName("Pet4");
        pet5.setName("Pet5");

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
		assertEquals(0, owner.getPetsInternal().size()); // for empty set of pets

        owner.setPetsInternal(pets);

		assertEquals(owner.getPetsInternal().size(), pets.size());
        assertTrue(owner.getPetsInternal().containsAll(pets));
        assertTrue(pets.containsAll(owner.getPetsInternal()));

    }

    @Test
    public void getPetsTest(){
		assertEquals(0, owner.getPets().size()); // for empty set of pets

        owner.setPetsInternal(pets);

		assertEquals(owner.getPets().size(), pets.size());
        assertTrue(owner.getPets().containsAll(pets));
        assertTrue(pets.containsAll(owner.getPets()));
    }

    @Test
    public void addPetTest(){
        List<Pet> petsBeforeAdd = owner.getPets();

        owner.addPet(pet5);

		assertEquals(owner.getPets().size(), petsBeforeAdd.size() + 1);
        assertTrue(owner.getPets().containsAll(petsBeforeAdd));
        assertTrue(owner.getPets().contains(pet5));

        owner.addPet(pet5); // add duplicate pet

		assertEquals(owner.getPets().size(), petsBeforeAdd.size() + 1);
        assertTrue(owner.getPets().containsAll(petsBeforeAdd));
        assertTrue(owner.getPets().contains(pet5));
    }

    @Test
    public void removePetTest(){
        owner.setPetsInternal(pets);
        List<Pet> petsBeforeRemove = owner.getPets();

        owner.removePet(pet4);

		assertEquals(owner.getPets().size(), petsBeforeRemove.size() - 1);
        assertTrue(petsBeforeRemove.containsAll(owner.getPets()));
		assertFalse(owner.getPets().contains(pet4));

        owner.removePet(pet4); // remove duplicate pet

		assertEquals(owner.getPets().size(), petsBeforeRemove.size() - 1);
        assertTrue(petsBeforeRemove.containsAll(owner.getPets()));
		assertFalse(owner.getPets().contains(pet4));
    }

    @Test
    public void getPetTest(){
        owner.setPetsInternal(pets);

        assertEquals(owner.getPet("Pet1") , pet1);
		assertNull(owner.getPet("Pet1000"));
    }

    @Test
    public void getPetWithIgnoreNewTest(){
        owner.setPetsInternal(pets);

		assertNull(owner.getPet("Pet1", true));
		assertEquals(owner.getPet("Pet1", false) , pet1);

        owner.getPet("Pet1", false).setId(1);

        assertEquals(owner.getPet("Pet1", true) , pet1);
		assertEquals(owner.getPet("Pet1", false) , pet1);

		assertNull(owner.getPet("Pet1000", true));
		assertNull(owner.getPet("Pet1000", false));
    }
}
