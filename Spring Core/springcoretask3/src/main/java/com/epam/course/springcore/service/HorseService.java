package com.epam.course.springcore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.epam.course.springcore.domain.Horse;
import com.epam.course.springcore.util.Util;


public class HorseService {

	/*
	 * search Horse by name
	 */
	public Horse searchHorseByName() {

		System.out.println("Enter the name of the horse :");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();

		List<Horse> horses = Util.getAllHorses();
		Optional<Horse> optional = horses.stream().filter(h -> h.getName().equalsIgnoreCase(str)).findAny();

		if (optional.isPresent()) {
			System.out.println("Horse Name :" + optional.get().getName());
			return optional.get();
		} else {
			System.out.println("No hourse found");
			userSearchOptions();
			return null;
		}
	}

	/*
	 * search Horse by Breed
	 */
	public Horse searchHorseByBreed() {
		System.out.println("Enter the name of the Breed :");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();

		List<Horse> horses = Util.getAllHorses();
		Optional<Horse> optional = horses.stream().filter(h -> h.getBreed().toString().
				equalsIgnoreCase(str)).findAny();

		if (optional.isPresent()) {
			System.out.println("Horse Name :" + optional.get().getName());
			return optional.get();
		} else {
			System.out.println("No hourse found");
			userSearchOptions();
			return null;
		}
	}

	/*
	 * search Horse by Rider name
	 */
	public Horse searchHorseByRider() {
		System.out.println("Enter the name of the Rider :");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();

		List<Horse> horses = Util.getAllHorses();
		Optional<Horse> optional = horses.stream().filter(h -> h.getRider().getName().
				equalsIgnoreCase(str)).findAny();

		if (optional.isPresent()) {
			System.out.println("Horse Name :" + optional.get().getName());
			return optional.get();
		} else {
			System.out.println("No hourse found");
			userSearchOptions();
			return null;
		}
	}

	public List<Horse> getAvailableHorses() {
		List<Horse> horses = Util.getAllHorses();
		Set<Horse> horsesForRace = new HashSet<>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 5; i++) {
			int randomInt = randomGenerator.nextInt(15);
			horsesForRace.add(horses.get(randomInt));
			if (i == 4 && horsesForRace.size() < 5) {
				i--;
			}
		}
		List<Horse> availableHorses = new ArrayList<>();
		availableHorses.addAll(horsesForRace);
		/*System.out.println("Available Horse for Race :");
		for (int i = 0; i < availableHorses.size(); i++) {
			System.out.println("No. " + (i + 1) + "\t Name : " + availableHorses.get(i).getName() + "\t Breed : "
					+ availableHorses.get(i).getBreed());
		}*/
		return availableHorses;
	}

	public Horse selectHorse(List<Horse> horses) {
		Scanner in = new Scanner(System.in);
		System.out.println("\n select the horse to bet from below :");
		int a = in.nextInt();
		// System.out.println("You entered integer " + a);
		System.out.println("selected Hourse : " + horses.get(a - 1).getName());
		return horses.get(a - 1);
	}

	public Horse userSearchOptions() {
		System.out.println("-----choose one option to select a horse -----");
		System.out.println("1 . Search Horse by Name");
		System.out.println("2 . Search Horse by breed");
		System.out.println("3 . Search by Rider's name");
		System.out.println("4 . Select from Available Horses");
		Horse horse = null;
		Scanner in = new Scanner(System.in);
		int opt = in.nextInt();

		switch (opt) {
		case 1:
			horse = searchHorseByName();
			break;
		case 2:
			horse = searchHorseByBreed();
			break;
		case 3:
			horse = searchHorseByRider();
			break;
		case 4:
			List<Horse> horses = Util.getAllHorses();
			for (int i = 0; i < horses.size(); i++) {
				System.out.println("No. " + (i + 1) + "\t Name : " + horses.get(i).getName() + "\t Breed : "
						+ horses.get(i).getBreed());
			}
			horse = selectHorse(horses);
			break;
		default:
			System.out.println("Choose valid option");
			userSearchOptions();
		}
		return horse;
	}
}
