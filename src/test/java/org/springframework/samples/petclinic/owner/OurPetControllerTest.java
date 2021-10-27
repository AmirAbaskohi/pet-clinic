package org.springframework.samples.petclinic.owner;

import org.junit.*;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class OurPetControllerTest {

	private PetRepository mockPets;
	private OwnerRepository mockOwners;
	private PetManager mockPetManager;

	private PetController petController;
	@Before
	public void setup(){
		mockPets = mock(PetRepository.class);
		mockOwners = mock(OwnerRepository.class);
		mockPetManager = mock(PetManager.class);
		petController = new PetController(mockPets, mockOwners, mockPetManager);
	}

	@Test
	public void initUpdateFromStateTest(){
		Pet mockPet = mock(Pet.class);
		ModelMap mockModelMap = mock(ModelMap.class);

		when(mockPetManager.findPet(5)).thenReturn(mockPet);

		assertEquals("pets/createOrUpdatePetForm", petController.initUpdateForm(5, mockModelMap));
	}

	@Test
	public void initUpdateFromBehaviorTest(){
		Pet mockPet = mock(Pet.class);
		ModelMap mockModelMap = mock(ModelMap.class);

		when(mockPetManager.findPet(5)).thenReturn(mockPet);

		petController.initUpdateForm(5, mockModelMap);

		verify(mockPetManager).findPet(5);
		verify(mockModelMap).put("pet", mockPet);
	}
}
