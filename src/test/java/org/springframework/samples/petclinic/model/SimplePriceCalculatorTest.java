package org.springframework.samples.petclinic.model;

import org.junit.*;
import org.springframework.samples.petclinic.model.priceCalculators.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SimplePriceCalculatorTest {
	SimplePriceCalculator simplePriceCalculator;
	private List<Pet> pets;
	private double baseCharge;
	private double basePricePerPet;
	private Pet pet1;
	private PetType petType;

	private double delta = 0.0001;

	@Before
	public void setup(){
		pets = mock(ArrayList.class);
		pet1 = mock(Pet.class);
		petType = mock(PetType.class);
		baseCharge = 12.5;
		basePricePerPet = 3.5;
		simplePriceCalculator = new SimplePriceCalculator();
	}

	@Test
	public void calcPriceTestNoForNoIf(){
		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.GOLD);
		assertEquals(ans, 12.5, delta);
	}

	@Test
	public void calcPriceTestNoForSecondIf(){
		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.NEW);
		assertEquals(ans, 11.875, delta);
	}

	@Test
	public void calcPriceTestForFirstIfNoSecondIf(){
		when(pets.size()).thenReturn(1);
		when(pets.get(0)).thenReturn(pet1);
		when(pet1.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);

		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.GOLD);
		assertEquals(ans, 16.7, delta);
	}

	@Test
	public void calcPriceTestForFirstIfSecondIf(){
		when(pets.size()).thenReturn(1);
		when(pets.get(0)).thenReturn(pet1);
		when(pet1.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(true);

		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.NEW);
		assertEquals(ans, 15.865, delta);
	}

	@Test
	public void calcPriceTestForFirstElseNoSecondIf(){
		when(pets.size()).thenReturn(1);
		when(pets.get(0)).thenReturn(pet1);
		when(pet1.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);

		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.GOLD);
		assertEquals(ans, 16, delta);
	}

	@Test
	public void calcPriceTestForFirstElseSecondIf(){
		when(pets.size()).thenReturn(1);
		when(pets.get(0)).thenReturn(pet1);
		when(pet1.getType()).thenReturn(petType);
		when(petType.getRare()).thenReturn(false);

		double ans = simplePriceCalculator.calcPrice(pets, baseCharge, basePricePerPet, UserType.NEW);
		assertEquals(ans, 15.2, delta);
	}
}
