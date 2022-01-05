package bdd;

import io.cucumber.java.en.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class petServiceSteps {
	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	PetTimedCache pets;

	private int TEST_PET_ID = 1;
	private Pet foundPet;
	private Pet newPet;
	private Pet pet;

	private Owner owner;
	private Owner foundOwner;

	private PetType petType;

	@Given("owner with id {int} exists")
	public void createOwnerWithId(int id) {
		owner = new Owner();
		owner.setId(id);
		owner.setFirstName("Reza");
		owner.setLastName("Reza Zade");
		owner.setAddress("Azadi");
		owner.setCity("Tehran");
		owner.setTelephone("123");
		ownerRepository.save(owner);
	}

	@When("finding for owner")
	public void findingForOwner() {
		foundOwner = petService.findOwner(owner.getId());
	}

	@Then("returned owner is not null and has id {int}")
	public void checkReturnedOwner(int id) {
		assertEquals(id, foundOwner.getId());
	}




	@Given("an owner exists with any pet")
	public void createOwnerWithAnyPet(){
		owner = new Owner();
	}

	@When("request an new pet for this owner")
	public void requestForNewPet(){
		newPet = petService.newPet(owner);
	}

	@Then("pet added to owner successfully")
	public void checkPetAdded(){
		assertEquals(newPet ,owner.getPets().get(0));
	}




	@Given("pet with id {int} exists")
	public void createPetWithId(int petId) {
		pet = new Pet();
		petType = new PetType();
		petType.setId(1);
		owner = new Owner();
		owner.setId(1);

		pet.setName("black");
		pet.setBirthDate(LocalDate.of(2020, 12, 12));
		pet.setType(petType);
		pet.setId(petId);
		owner.addPet(pet);

		pets.save(pet);
	}

	@When("finding for pet")
	public void findingForPet() {
		foundPet = petService.findPet(pet.getId());
	}

	@Then("returned pet is not null and has id {int}")
	public void checkReturnedPet(int petId) {
		assertEquals(petId, foundPet.getId());
	}




	@Given("an owner with any pet and a pet exists")
	public void createOwnerWithAnyPetAndNewPet(){
		owner = new Owner();
		owner.setId(1);
		owner.setFirstName("Reza");
		owner.setLastName("Reza Zade");
		owner.setAddress("Azadi");
		owner.setCity("Tehran");
		owner.setTelephone("123");

		pet = new Pet();
		petType = new PetType();
		petType.setId(1);
		pet.setName("black");
		pet.setBirthDate(LocalDate.of(2020, 12, 12));
		pet.setType(petType);

	}

	@When("save the pet to this owner")
	public void requestForSavePet(){
		petService.savePet(pet, owner);
	}

	@Then("pet saved to owner successfully")
	public void checkPetSaved(){
		assertEquals(pet ,owner.getPets().get(0));
	}
}
