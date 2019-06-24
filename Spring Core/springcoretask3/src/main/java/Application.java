import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.course.springcore.config.AppConfig;
import com.epam.course.springcore.domain.Horse;
import com.epam.course.springcore.domain.Race;
import com.epam.course.springcore.service.EmulationService;
import com.epam.course.springcore.service.FinishLineService;
import com.epam.course.springcore.service.HorseService;
import com.epam.course.springcore.service.RaceService;

public class Application {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = 
        new AnnotationConfigApplicationContext(AppConfig.class);

		RaceService raceService = (RaceService) ctx.getBean(RaceService.class);
		Race race = raceService.getRace();
		System.out.println("********Race Details********");
		System.out.println("Race Name: " + race.getName());
		System.out.println("Race Distance: " + race.getRaceLength() + "kms");
		HorseService horseService = (HorseService) ctx.getBean(HorseService.class);
		Horse selHorse = horseService.userSearchOptions();
		List<Horse> horses = horseService.getAvailableHorses();
		horses = horses.stream().filter(h -> !(h.getName().equals(selHorse.getName()))).collect(Collectors.toList());
			horses.add(selHorse);
		System.out.println("Available Horse for Race :");
		for (int i = 0; i < horses.size(); i++) {
			System.out.println("No. " + (i + 1) + "\t Name : " + horses.get(i).getName() + "\t Breed : "
					+ horses.get(i).getBreed());
		}
		// Horse selHorse = horseService.selectHorse(horses);

		// Race starting now

		ExecutorService e = Executors.newFixedThreadPool(horses.size());
		FinishLineService finishLineService = new FinishLineService();
		for (int i = 0; i < horses.size(); i++) {
			EmulationService emulationService = (EmulationService) ctx.getBean(EmulationService.class);
			emulationService.setHorse(horses.get(i));
			emulationService.setDistance(race.getRaceLength());
			emulationService.setFinishLineService(finishLineService);
			e.execute(emulationService);
		}
		e.shutdown();
		while (true) {
			if (e.isTerminated()) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finishLineService.print();
				List<Horse> ranking = finishLineService.getRanking();
				while (ranking.size() != horses.size()) {

					ranking = finishLineService.getRanking();
				}

				System.out.println("Your horse Rank :" + (ranking.indexOf(selHorse) + 1));
				if ((ranking.indexOf(selHorse) + 1) == 1) {
					System.out.println("***Congrats!!!!*** YOU WON");
				} else {
					System.out.println("***Better Luck Next Time*****");
				}
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
