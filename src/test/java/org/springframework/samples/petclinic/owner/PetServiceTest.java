package org.springframework.samples.petclinic.owner;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RunWith(Parameterized.class)
class PetServiceTest {

    private final PetTimedCache cache;

    @Mock
    private OwnerRepository ownerRepository;
    private final PetService petService;
    
    static Pet pet1 = new Pet();
	static Pet pet2 = new Pet();
    static Pet pet3 = new Pet();
    static Pet pet4 = new Pet();
    static Pet pet5 = new Pet();
    static Pet pet6 = new Pet();
    static Pet pet7 = new Pet();
    static Pet pet8 = new Pet();

    public PetServiceTest() {
        super();
        cache = mock(PetTimedCache.class);
        Logger logger = LoggerFactory.getLogger("test");
        petService = new PetService(cache, ownerRepository, logger);
    }

    @BeforeEach
    public void setup() {
        pet1.setName("Pet1");
        pet2.setName("Pet2");
        pet3.setName("Pet3");
        pet4.setName("Pet4");
        pet5.setName("Pet5");
        pet6.setName("Pet6");
        pet7.setName("Pet7");
        pet8.setName("Pet8");

        when(cache.get(1)).thenReturn(pet1);
        when(cache.get(2)).thenReturn(pet2);
        when(cache.get(3)).thenReturn(pet3);
        when(cache.get(4)).thenReturn(pet4);
        when(cache.get(5)).thenReturn(pet5);
        when(cache.get(6)).thenReturn(pet6);
        when(cache.get(7)).thenReturn(pet7);
        when(cache.get(8)).thenReturn(pet8);
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
        assertEquals(petService.findPet(this.petId), this.pet);
    }

}
