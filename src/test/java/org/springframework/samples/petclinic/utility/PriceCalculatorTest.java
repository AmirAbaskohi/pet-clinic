package org.springframework.samples.petclinic.utility;

import org.junit.*;
import org.springframework.samples.petclinic.owner.Pet;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class PriceCalculatorTest {

	@Before
	void setup(){
		Pet pet1 = mock(Pet.class);
		Pet pet2 = mock(Pet.class);
		Pet pet3 = mock(Pet.class);

		when(pet1.getBirthDate()).thenReturn(LocalDate.of(2015, 1, 1));
		when(pet2.getBirthDate()).thenReturn(LocalDate.of(2016, 1, 1));
		when(pet3.getBirthDate()).thenReturn(LocalDate.of(2017, 1, 1));
	}

}
