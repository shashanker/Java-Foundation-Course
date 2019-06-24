package com.epam.course.springcore.util;

import java.util.ArrayList;
import java.util.List;

import com.epam.course.springcore.domain.Breed;
import com.epam.course.springcore.domain.Horse;
import com.epam.course.springcore.domain.Race;
import com.epam.course.springcore.domain.Rider;

/*
 * Abtenauer,
	Albanian,
	Appaloosa,
	Auxois,
	Balikun,
	Bardigiano,
	Barb,
	Boerperd,
	Brumby,
	Calabrese,
	Friesian,
	Falabella,
	Kladruber,
	Knabstrupper,
	Holsteiner
 */

public class Util {
	
	public static List<Horse> getAllHorses() {
		
		List<Horse> horses = new ArrayList<Horse>();
		Horse h1 = new Horse();
		h1.setId(1);
		h1.setName("Arrrrr");
		h1.setBreed(Breed.Abtenauer);
		Rider rider = new Rider();
		rider.setName("Peter Thomsen");
		h1.setRider(rider);
		horses.add(h1);
		
		Horse h2 = new Horse();
		h2.setId(2);
		h2.setName("Bofa Deez Nuts");
		h2.setBreed(Breed.Albanian);
		rider = new Rider();
		rider.setName("Andreas Dibowski");
		h2.setRider(rider);
		horses.add(h2);
		
		
		Horse h3 = new Horse();
		h3.setId(3);
		h3.setName("Covfefe");
		h3.setBreed(Breed.Appaloosa);
		rider = new Rider();
		rider.setName("Frank Ostholt");
		h3.setRider(rider);
		horses.add(h3);
		
		
		Horse h4 = new Horse();
		h4.setId(4);
		h4.setName("Denman’s Call");
		h4.setBreed(Breed.Auxois);
		rider = new Rider();
		rider.setName("Steffen Peters");
		h4.setRider(rider);
		horses.add(h4);
		
		Horse h5 = new Horse();
		h5.setId(5);
		h5.setName("Doremifasollatido");
		h5.setBreed(Breed.Balikun);
		rider = new Rider();
		rider.setName("Abdullah bin Mutaib");
		h5.setRider(rider);
		horses.add(h5);
		
		Horse h6 = new Horse();
		h6.setId(6);
		h6.setName("Dr. Fager");
		h6.setBreed(Breed.Barb);
		rider = new Rider();
		rider.setName("Edward Gal");
		h6.setRider(rider);
		horses.add(h6);
		
		Horse h7 = new Horse();
		h7.setId(7);
		h7.setName("Effinex");
		h7.setBreed(Breed.Bardigiano);
		rider = new Rider();
		rider.setName("Ian Millar");
		h7.setRider(rider);
		horses.add(h7);
		
		Horse h8 = new Horse();
		h8.setId(8);
		h8.setName("Fiftyshadesofhay");
		h8.setBreed(Breed.Boerperd);
		rider = new Rider();
		rider.setName("Carl Bouckaert");
		h8.setRider(rider);
		horses.add(h8);
		
		Horse h9 = new Horse();
		h9.setId(9);
		h9.setName("First Dude");
		h9.setBreed(Breed.Brumby);
		rider = new Rider();
		rider.setName("Eric Lamaze");
		h9.setRider(rider);
		horses.add(h9);
		
		Horse h10 = new Horse();
		h10.setId(10);
		h10.setName("Flat Drunk");
		h10.setBreed(Breed.Calabrese);
		rider = new Rider();
		rider.setName("Andrew Nicholson");
		h10.setRider(rider);
		horses.add(h10);
		
		Horse h11 = new Horse();
		h11.setId(11);
		h11.setName("Flat Fleet Feet");
		h11.setBreed(Breed.Falabella);
		rider = new Rider();
		rider.setName("Rich Fellers");
		h11.setRider(rider);
		horses.add(h11);
		
		Horse h12 = new Horse();
		h12.setId(12);
		h12.setName("Harass");
		h12.setBreed(Breed.Friesian);
		rider = new Rider();
		rider.setName("Billy Twomey");
		h12.setRider(rider);
		horses.add(h12);
		
		Horse h13 = new Horse();
		h13.setId(13);
		h13.setName("Hoof Hearted");
		h13.setBreed(Breed.Holsteiner);
		rider = new Rider();
		rider.setName("Joseph Murphy");
		h13.setRider(rider);
		horses.add(h13);
		
		Horse h14 = new Horse();
		h14.setId(14);
		h14.setName("Midnight Lute");
		h14.setBreed(Breed.Kladruber);
		rider = new Rider();
		rider.setName("Stefano Brecciaroli");
		h14.setRider(rider);
		horses.add(h14);
		
		
		Horse h15 = new Horse();
		h15.setId(15);
		h15.setName("Notacatbutallama");
		h15.setBreed(Breed.Knabstrupper);
		rider = new Rider();
		rider.setName("Michael Jung");
		h15.setRider(rider);
		horses.add(h15);
		
		return horses;
	}

	public static List<Race> getAllRaces() {
		
		List<Race> races = new ArrayList<Race>();
		
		Race r1 = new Race();
		r1.setId(1);
		r1.setName("Grand Prix de Bruxelles");
		r1.setRaceLength(100);
		races.add(r1);
		
		Race r2 = new Race();
		r2.setId(2);
		r2.setName("Svenskt Derby");
		r2.setRaceLength(100);
		races.add(r2);
		
		Race r3 = new Race();
		r3.setId(3);
		r3.setName("Breeders' Stakes");
		r3.setRaceLength(150);
		races.add(r3);
		
		Race r4 = new Race();
		r4.setId(4);
		r4.setName("Doncaster Cup");
		r4.setRaceLength(200);
		races.add(r4);
		
		Race r5 = new Race();
		r5.setId(5);
		r5.setName("Royal Ascot");
		r5.setRaceLength(250);
		races.add(r5);
		
		return races;
		
	}
}
