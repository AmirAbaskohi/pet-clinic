package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;
import org.springframework.samples.petclinic.visit.Visit;


public class PetManagerTest {
	private PetTimedCache mockPets;
	private OwnerRepository mockOwners;
	private Logger mockLogger;
	private PetManager petManager;

	@Before
	public void setup(){
		mockPets = mock(PetTimedCache.class);
		mockOwners = mock(OwnerRepository.class);
		mockLogger = mock(Logger.class);
		petManager = new PetManager(mockPets, mockOwners, mockLogger);
	}

	/** stub + dummy , state verification, Mockisty **/
	@Test
	public void findOwnerStateTest(){
		Owner dummyOwner = mock(Owner.class); //dummy

		when(mockOwners.findById(5)).thenReturn(dummyOwner);
		assertSame(petManager.findOwner(5), dummyOwner);
	}

	/** spy , behavior verification, Mockisty **/
	@Test
	public void findOwnerBehaviorTest(){
		petManager.findOwner(5);
		verify(mockLogger).info("find owner {}", 5);
	}

	/** stub + dummy , behavior verification, Mockisty **/
	@Test
	public void newPetReturnBehaviorTest(){
		Owner mockOwner = mock(Owner.class);
		doReturn(6).when(mockOwner).getId();
		petManager.newPet(mockOwner);
		verify(mockLogger).info("add pet for owner {}", 6);
	}

	/** spy , behavior verification, Mockisty **/
	@Test
	public void newPetCallOwnerAddPetBehaviorTest() {
		Owner spyOwner = spy(Owner.class);
		petManager.newPet(spyOwner);
		verify(spyOwner).addPet(any(Pet.class));
	}

	/** stub + dummy , state verification, Mockisty **/
	@Test
	public void findPetStateTest(){
		Pet dummyPet = mock(Pet.class);
		when(mockPets.get(5)).thenReturn(dummyPet);
		assertSame(petManager.findPet(5), dummyPet);
	}

	/** spy , behavior verification, Mockisty **/
	@Test
	public void findPetBehaviorTest(){
		petManager.findPet(5);
		verify(mockLogger).info("find pet by id {}", 5);
	}

	/** spy, behavior verification, classical **/
	@Test
	public void savePetLoggerBehaviorTest(){
		Owner owner = new Owner();
		Pet spyPet = spy(Pet.class);
		doReturn(12).when(spyPet).getId();
		petManager.savePet(spyPet, owner);
		verify(spyPet).getId();
		verify(mockLogger).info("save pet {}", 12);
	}

	/** spy , behavior verification, classical **/
	@Test
	public void savePetCallAddPetBehaviorTest(){
		Owner spyOwner = spy(Owner.class);
		Pet pet = new Pet();
		petManager.savePet(pet, spyOwner);
		verify(spyOwner).addPet(pet);
	}

	/** spy + dummy , behavior verification, Mockisty **/
	@Test
	public void savePetBehaviorTest(){
		Owner dummyOwner = mock(Owner.class);
		Pet dummyPet = mock(Pet.class);
		petManager.savePet(dummyPet, dummyOwner);
		verify(mockPets).save(dummyPet);
	}

	/** stub + dummy, state verification, Mockisty **/
	@Test
	public void getOwnerPetsStateTest(){
		Pet dummyPet1 = mock(Pet.class);
		Pet dummyPet2 = mock(Pet.class);
		List<Pet> dummyPets = new ArrayList<Pet>(){{add(dummyPet1); add(dummyPet2); }};
		Owner mockOwner = mock(Owner.class);

//		when(petManager.findOwner(5)).thenReturn(mockOwner); ?????????????
		when(mockOwners.findById(5)).thenReturn(mockOwner);
		when(mockOwner.getPets()).thenReturn(dummyPets);

		assertEquals(dummyPets, petManager.getOwnerPets(5));
	}

	/** spy + dummy, behavior verification, Mockisty **/
	@Test
	public void getOwnerPetsLoggerBehaviorTest(){
		Owner dummyOwner = mock(Owner.class);
		when(mockOwners.findById(5)).thenReturn(dummyOwner);
		petManager.getOwnerPets(5);
		verify(mockLogger).info("finding the owner's pets by id {}", 5);
	}

	/** stub + dummy, state verification, Mockisty **/
	@Test
	public void getOwnerPetTypesStateTest(){
		PetType dummyPetType1 = mock(PetType.class);
		PetType dummyPetType2 = mock(PetType.class);
		Pet mockPet1 = mock(Pet.class);
		Pet mockPet2 = mock(Pet.class);
		List<Pet> mockPets = new ArrayList<Pet>(){{add(mockPet1); add(mockPet2); }};
		Set<PetType> dummyPetTypes = new HashSet<PetType>(){{add(dummyPetType1); add(dummyPetType2);}};
		Owner mockOwner = mock(Owner.class);

		when(mockOwners.findById(5)).thenReturn(mockOwner);
		when(mockOwner.getPets()).thenReturn(mockPets);
		when(mockPet1.getType()).thenReturn(dummyPetType1);
		when(mockPet2.getType()).thenReturn(dummyPetType2);

		assertEquals(dummyPetTypes, petManager.getOwnerPetTypes(5));
	}

	/** spy + dummy, behavior verification, Mockisty **/
	@Test
	public void getOwnerPetTypesLoggerBehaviorTest(){
		Pet dummyPet1 = mock(Pet.class);
		Pet dummyPet2 = mock(Pet.class);
		List<Pet> dummyPets = new ArrayList<Pet>(){{add(dummyPet1); add(dummyPet2); }};
		Owner mockOwner = mock(Owner.class);

		when(mockOwners.findById(5)).thenReturn(mockOwner);
		when(mockOwner.getPets()).thenReturn(dummyPets);

		petManager.getOwnerPetTypes(5);

		verify(mockLogger).info("finding the owner's petTypes by id {}", 5);
	}

	/** stub + dummy, state verification, classical **/
	@Test
	public void getVisitsBetweenStateTest(){
		Pet mockPet = mock(Pet.class);
		LocalDate startDate = LocalDate.of(2020, 11, 1);
		LocalDate endDate = LocalDate.of(2021, 12, 5);

		Visit dummyVisit1 = new Visit();
		Visit dummyVisit2 = new Visit();
		List<Visit> dummyVisits = new ArrayList<Visit>(){{add(dummyVisit1); add(dummyVisit2); }};

		when(mockPets.get(5)).thenReturn(mockPet);
		when(mockPet.getVisitsBetween(startDate, endDate)).thenReturn(dummyVisits);

		assertEquals(dummyVisits, petManager.getVisitsBetween(5, startDate, endDate));
	}

	/** spy + dummy, behavior verification, classical **/
	@Test
	public void getVisitsBetweenLoggerBehaviorTest(){
		Pet dummyPet = mock(Pet.class);
		LocalDate startDate = LocalDate.of(2020, 11, 1);
		LocalDate endDate = LocalDate.of(2021, 12, 5);

		when(mockPets.get(5)).thenReturn(dummyPet);
		petManager.getVisitsBetween(5, startDate, endDate);

		verify(mockLogger).info("get visits for pet {} from {} since {}", 5, startDate, endDate);
	}
}
