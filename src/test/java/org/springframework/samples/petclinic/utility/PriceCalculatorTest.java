package org.springframework.samples.petclinic.utility;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PriceCalculatorTest {

	private double delta = 0.0001;

	Pet pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8, pet9, pet10;
	List<Pet> pets;
	List<Visit> visits, visits1, visits2, visits3, visits4;
	Visit visit1, visit2, visit3, visit4;

	@Before
	public void setup(){
		pet1 = mock(Pet.class);
		pet2 = mock(Pet.class);
		pet3 = mock(Pet.class);
		pet4 = mock(Pet.class);
		pet5 = mock(Pet.class);
		pet6 = mock(Pet.class);
		pet7 = mock(Pet.class);
		pet8 = mock(Pet.class);
		pet9 = mock(Pet.class);
		pet10 = mock(Pet.class);

		pets = new ArrayList<>();
		visits = new ArrayList<>();
		visits1 = new ArrayList<>();
		visits2 = new ArrayList<>();
		visits3 = new ArrayList<>();
		visits4 = new ArrayList<>();

		visit1 = mock(Visit.class);
		visit2 = mock(Visit.class);
		visit3 = mock(Visit.class);
		visit4 = mock(Visit.class);

		when(visit1.getDate()).thenReturn(LocalDate.now());
		when(visit2.getDate()).thenReturn(LocalDate.now().minusDays(50));
		when(visit3.getDate()).thenReturn(LocalDate.now().minusDays(100));
		when(visit4.getDate()).thenReturn(LocalDate.now().minusDays(200));

	}
	@Test
	public void emptyPetsTest(){
		double ans = PriceCalculator.calcPrice(pets, 1, 1);
		assertEquals(0,ans, delta);
	}

	@Test
	public void onePetNoVisitAgeMoreThan2Test(){
		when(pet1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet1.getBirthDate()).thenReturn(LocalDate.of(2015, 1, 1));

		pets.add(pet1);

		double ans = PriceCalculator.calcPrice(pets, 1.5, 1.5);
		assertEquals(1.8, ans, delta);
	}

	@Test
	public void onePetNoVisitAge2Test(){
		when(pet1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet1.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));

		pets.add(pet1);

		double ans = PriceCalculator.calcPrice(pets, 1.5, 1.5);
		assertEquals(2.52, ans, delta);
	}

	@Test
	public void onePetNoVisitAgeLessThan2Test(){
		when(pet1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet1.getBirthDate()).thenReturn(LocalDate.now().minusDays(1));

		pets.add(pet1);

		double ans = PriceCalculator.calcPrice(pets, 1.5, 1.5);
		assertEquals(2.52, ans, delta);
	}

	@Test
	public void fivePetAges2Test(){
		when(pet1.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet2.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet3.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet4.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet5.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);
		pets.add(pet5);

		when(pet1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet2.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet3.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet4.getVisitsUntilAge(anyInt())).thenReturn(visits);

		double ans = PriceCalculator.calcPrice(pets, 1.5, 1.5);
		assertEquals(24.18, ans, delta);
	}

	@Test
	public void tenPetAges2Test(){
		when(pet1.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet2.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet3.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet4.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet5.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));

		when(pet6.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet7.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet8.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
		when(pet9.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));
//		when(pet10.getBirthDate()).thenReturn(LocalDate.now().minusYears(2));

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);
		pets.add(pet5);
		pets.add(pet6);
		pets.add(pet7);
		pets.add(pet8);
		pets.add(pet9);
//		pets.add(pet5);

		visits1.add(visit1);
		visits2.addAll(visits1);
		visits2.add(visit2);
		visits3.addAll(visits2);
		visits3.add(visit3);
		visits4.addAll(visits3);
		visits4.add(visit4);

		when(pet1.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet2.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet3.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet4.getVisitsUntilAge(anyInt())).thenReturn(visits);
		when(pet5.getVisitsUntilAge(anyInt())).thenReturn(visits1);
		when(pet6.getVisitsUntilAge(anyInt())).thenReturn(visits2);
		when(pet7.getVisitsUntilAge(anyInt())).thenReturn(visits3);
		when(pet8.getVisitsUntilAge(anyInt())).thenReturn(visits4);

		double ans = PriceCalculator.calcPrice(pets, 1.5, 1.5);
		assertEquals(2643.54, ans, delta);
	}
}
