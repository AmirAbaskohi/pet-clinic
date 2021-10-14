package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

@RunWith(Parameterized.class)
class PetServiceTest {
    
    private PetService petService;

    static Pet pet1 = new Pet();
	static Pet pet2 = new Pet();
    static Pet pet3 = new Pet();
    static Pet pet4 = new Pet();
    static Pet pet5 = new Pet();
    static Pet pet6 = new Pet();
    static Pet pet7 = new Pet();
    static Pet pet8 = new Pet();

    @Before
    public void setup() {
        pet1.setId(1);
        pet2.setId(2);
        pet3.setId(3);
        pet4.setId(4);
        pet5.setId(5);
        pet6.setId(6);
        pet7.setId(7);
        pet8.setId(8);

        pet1.setName("Pet1");
        pet2.setName("Pet2");
        pet3.setName("Pet3");
        pet4.setName("Pet4");
        pet5.setName("Pet5");
        pet6.setName("Pet6");
        pet7.setName("Pet7");
        pet8.setName("Pet8");
    }

    @Parameterized.Parameter(value=0)
    public int petId;

    @Parameterized.Parameter(value = 1)
    public Pet pet;

    @Parameterized.Parameters(name = "{index} : pet with petId {0} = {1}")
    public static Iterable<Object []> data()
    {
        return Arrays.asList(new Object[][] {
            {1, pet1},
            {2, pet2},
            {3, pet3},
            {4, pet4},
            {5, pet5},
            {6, pet6},
            {7, pet7},
            {8, pet8}
        });
    }

    @Test
    public void findPetTest() {
        assertEquals(petService.findPet(this.petId).getName(), this.pet.getName());
    }

}
