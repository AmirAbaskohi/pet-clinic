package org.springframework.samples.petclinic.model;

import org.junit.*;
import org.springframework.samples.petclinic.model.priceCalculators.CustomerDependentPriceCalculator;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CustomerDependentPriceCalculatorTest {
	CustomerDependentPriceCalculator customerDependentPriceCalculator;
	private List<Pet> pets;
	private double baseCharge;
	private double basePricePerPet;
	private Pet pet1, pet2, pet3;
	private PetType petType1, petType2;
	private Date date1;
	private Date date2;

	private double delta = 0.0001;

	@Before
	public void setup(){
		pets = new ArrayList<>();
		pet1 = mock(Pet.class);
		pet2 = mock(Pet.class);
		pet3 = mock(Pet.class);
		petType1 = mock(PetType.class);
		petType2 = mock(PetType.class);
		date1 = new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime();
		date2 = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
		baseCharge = 12.5;
		basePricePerPet = 3.5;
		customerDependentPriceCalculator = new CustomerDependentPriceCalculator();
	}

	@Test
	public void calcPriceTestWholeForForthFifthIf(){
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);

		when(petType1.getRare()).thenReturn(true);
		when(petType2.getRare()).thenReturn(false);

		when(pet1.getType()).thenReturn(petType1);
		when(pet1.getBirthDate()).thenReturn(date2);

		when(pet2.getType()).thenReturn(petType1);
		when(pet2.getBirthDate()).thenReturn(date1);

		when(pet3.getType()).thenReturn(petType2);
		when(pet3.getBirthDate()).thenReturn(date2);

		double ans = customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.NEW);
		assertEquals(ans, 42.824, delta);
	}

	@Test
	public void calcPriceTestWholeForForthIfThirdElse(){
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);

		when(petType1.getRare()).thenReturn(true);
		when(petType2.getRare()).thenReturn(false);

		when(pet1.getType()).thenReturn(petType1);
		when(pet1.getBirthDate()).thenReturn(date2);

		when(pet2.getType()).thenReturn(petType1);
		when(pet2.getBirthDate()).thenReturn(date1);

		when(pet3.getType()).thenReturn(petType2);
		when(pet3.getBirthDate()).thenReturn(date1);

		double ans = customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.GOLD);
		assertEquals(ans, 34.976, delta);
	}

	@Test
	public void calcPriceTestNoForFirstElseIf(){
		double ans = customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.GOLD);
		assertEquals(ans, 12.5, delta);
	}

	@Test
	public void calcPriceTestNoForNoIfNoElseIf(){
		double ans = customerDependentPriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.NEW);
		assertEquals(ans, 0, delta);
	}
}
