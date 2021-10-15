package org.springframework.samples.petclinic.owner;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.junit.Assume.assumeNotNull;

import org.junit.*;
import org.junit.experimental.theories.*;
import org.junit.runner.*;

import java.util.*;

@RunWith(Theories.class)
public class OwnerTheoriesTest{
	private Owner owner;

	@Before
	public void setup() {
		owner = new Owner();
	}

	@DataPoints
	public static String[] petsName = {"pet1", "pet4", "pet7"};

	@DataPoints
	public static ArrayList[] sets=
		{
			new ArrayList<>(Arrays.asList("pet1", "pet2", "pet3")),
			new ArrayList<>(Arrays.asList("pet4", "pet5", "pet7", "pet8")),
			new ArrayList<>(),
		};


	@Theory
	public void getPetTestContain(String petsName, ArrayList<String> petsSetName){
		assumeNotNull(petsName);
		assumeNotNull(petsSetName);
		assumeTrue(petsSetName.contains(petsName));
		Set<Pet> petSet = new HashSet<>();
		for(String petName : petsSetName){
			Pet newPet = new Pet();
			newPet.setName(petName);
			petSet.add(newPet);
		}
		owner.setPetsInternal(petSet);
		assertEquals(petsName, owner.getPet(petsName).getName());
	}

	@Theory
	public void getPetTestNotContain(String petsName, ArrayList<String> petsSetName){
		assumeNotNull(petsName);
		assumeNotNull(petsSetName);
		assumeFalse(petsSetName.contains(petsName));
		Set<Pet> petSet = new HashSet<>();
		for(String petName : petsSetName){
			Pet newPet = new Pet();
			newPet.setName(petName);
			petSet.add(newPet);
		}
		owner.setPetsInternal(petSet);
		assertNull(owner.getPet(petsName));
	}
}
