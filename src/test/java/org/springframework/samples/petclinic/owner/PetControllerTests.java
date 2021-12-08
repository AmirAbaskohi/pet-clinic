package org.springframework.samples.petclinic.owner;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PetRepository pets;

	@MockBean
	private OwnerRepository owners;

	private Owner owner;
	private PetType petType;
	private Pet pet;

	@BeforeEach
	void setup() {

	owner = new Owner();
	owner.setId(1);
	petType = new PetType();
	petType.setId(1);
	petType.setName("petType");
	pet = new Pet();
	pet.setId(1);

	when(owners.findById(1)).thenReturn(owner);
	when(pets.findById(1)).thenReturn(pet);
	when(pets.findPetTypes()).thenReturn(Lists.newArrayList(petType));

	}

	@Test
	public void initCreationFormTest() throws Exception {
		ResultActions result = mockMvc.perform(get("/owners/1/pets/new"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("pets/createOrUpdatePetForm"));
		result.andExpect(model().attributeExists("pet"));
	}

	@Test
	public void processCreationFormTest() throws Exception {
		ResultActions result = mockMvc.perform(post("/owners/1/pets/new")
			.param("name", "name")
			.param("type", "petType")
			.param("birthDate", "2000-10-10"));
		result.andExpect(status().is3xxRedirection());
		result.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test
	public void initUpdateFormTest() throws Exception {
		ResultActions result = mockMvc.perform(post("/owners/1/pets/1/edit"));
		result.andExpect(status().isOk());
		result.andExpect(view().name("pets/createOrUpdatePetForm"));
		result.andExpect(model().attributeExists("pet"));
	}

	@Test
	public void processUpdateFormTest() throws Exception {
		ResultActions result = mockMvc.perform(post("/owners/1/pets/1/edit")
			.param("name", "name")
			.param("type", "petType")
			.param("birthDate", "2000-10-10"));
		result.andExpect(status().is3xxRedirection());
		result.andExpect(view().name("redirect:/owners/{ownerId}"));
	}
}
