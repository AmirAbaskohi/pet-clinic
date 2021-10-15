package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class PetTest {
	private Pet pet;

	@Before
	public void setup() {
		pet = new Pet();
	}

	@DataPoints
	public static ArrayList[] day=
		{
			new ArrayList<>(Arrays.asList(1, 5, 23)),
			new ArrayList<>(Arrays.asList(7, 28, 24, 9)),
			new ArrayList<>(Arrays.asList(7, 25, 17, 12, 8)),
			new ArrayList<>(),
		};

	@DataPoints
	public static ArrayList[] month=
		{
			new ArrayList<>(Arrays.asList(1, 3, 5)),
			new ArrayList<>(Arrays.asList(2, 2, 12, 12)),
			new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11)),
			new ArrayList<>(),
		};

	@DataPoints
	public static ArrayList[] year=
		{
			new ArrayList<>(Arrays.asList(1999, 2005, 2010)),
			new ArrayList<>(Arrays.asList(2000, 2000, 1990, 1990)),
			new ArrayList<>(Arrays.asList(2011, 2011, 2012, 2013, 2014)),
			new ArrayList<>(),
		};

	@Theory
	public void getVisitsTest(ArrayList<Integer> daySet, ArrayList<Integer> monthSet, ArrayList<Integer> yearSet){
		assumeNotNull(daySet);
		assumeNotNull(monthSet);
		assumeNotNull(yearSet);
		assumeTrue(daySet.size() == monthSet.size());
		assumeTrue(daySet.size() == yearSet.size());
		for(Integer day : daySet){
			assumeTrue(day <= 31);
		}
		for(Integer month : monthSet){
			assumeTrue(month <= 12);
		}
		for(Integer year : yearSet){
			assumeTrue(year >= 1900);
		}

		ArrayList<Visit> visits = new ArrayList<>();
		for (int i = 0; i < daySet.size(); i = i+1){
			int day = daySet.get(i);
			int month = monthSet.get(i);
			int year = yearSet.get(i);
			LocalDate newLocalDate = LocalDate.of(year, month, day);
			Visit newVisit = new Visit().setDate(newLocalDate);
			visits.add(newVisit);
			pet.addVisit(newVisit);
		}

		assertEquals(pet.getVisits().size(), visits.size());
		assertTrue(pet.getVisits().containsAll(visits));
		assertTrue(visits.containsAll(pet.getVisits()));

	}
}
