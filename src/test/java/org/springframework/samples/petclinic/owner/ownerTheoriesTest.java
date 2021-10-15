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
			new ArrayList<>(Arrays.asList("pet4", "pet5", "pet7", "")),
			new ArrayList<>(Arrays.asList("pet1", "pet2", "pet3")),
		};


	@Theory
	public void getPetTest(String petsName, ArrayList<String> petsSetName){
		assumeNotNull(petsName);
		assumeNotNull(petsSetName);
		assumeTrue(petsSetName.contains(petsName));
		Set<Pet> petSet = new HashSet<>();
		for(String petName : petsSetName){
			Pet newPet = new Pet();
			newPet.setName(petName);
			petSet.add(newPet);
		}
		System.out.println(petsName);
		owner.setPetsInternal(petSet);
		assertEquals(petsName, owner.getPet(petsName).getName());
	}
}
