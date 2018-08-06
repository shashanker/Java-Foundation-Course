package ua.epam.spring.hometask.ui.console.state;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Abstract state class that defined basic methods to get user input and call
 * for actions
 * 
 * @author Shashanker_Vaduka
 */
public abstract class AbstractState {
    public static final String DATE_TIME_INPUT_PATTERN = "yyyy-MM-dd HH:mm";
    
    public static final String DATE_INPUT_PATTERN = "yyyy-MM-dd HH:mm";
    
    private static Scanner scanner = new Scanner(System.in, "UTF-8");
    
    public void run() {
        printDefaultInformation();
        int action = 0;
        do {
            printDelimiter();
            System.out.println("What would you like to do?");
            int maxInput = printMainActions();
            System.out.println(" 0) Return");
            action = readUserActionInput(maxInput);
            System.out.println("");
            if (action > 0) {
                runAction(action);
            }
        } while (action > 0);
    }
    
    public void printDelimiter() {
        System.out.println("--------------------------------------------");
    }
    
    protected String readStringInput(String prefix) {
        System.out.print(prefix);
        String line = scanner.nextLine();
        return line;
    }
    
    protected <R> R readInput(String prefix, Function<String, R> convertFunction) {
        R input = null;
        do {
            String str = readStringInput(prefix);
            try {
                input = convertFunction.apply(str);
            } catch (Exception e) {
                System.err.println("Failed to convert: " + e.getMessage());
                input = null;
            }
        } while (input == null);
        return input;
    }

    protected int readIntInput(String prefix) {
        return readInput(prefix, s -> Integer.parseInt(s));
    }
    
    protected int readIntInput(final String prefix, final int max) {
        return readInput(prefix, s -> {
            int i = Integer.parseInt(s);
            if (i > max) {
                throw new IllegalArgumentException("Input can't be more than " + max);
            }
            if(i < 0){
            	throw new IllegalArgumentException("Input can't be less than 0 ");
            }
            return i;
        });
    }
    
    protected long readLongInput(String prefix) {
        return readInput(prefix, s -> Long.parseLong(s));
    }

    protected double readDoubleInput(String prefix) {
        return readInput(prefix, s -> Double.parseDouble(s));
    }
    
    protected LocalDateTime readDateTimeInput(String prefix) {
        return readInput(prefix, s -> LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN)));
    }
    
    protected String formatDateTime(LocalDateTime dt) {
        return DateTimeFormatter.ofPattern(DATE_TIME_INPUT_PATTERN).format(dt);
    }
    
    protected Date readDateInput(String prefix) {
        return readInput(prefix, (s) ->{ 
        	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        	Date date = null;
			try {
				date = format.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        System.out.println(date);	
       return date;});
    }
    

    protected abstract void printDefaultInformation();

    protected abstract int printMainActions();

    protected abstract void runAction(int action);
    
    private int readUserActionInput(int maxInput) {
        do {
            int action = readIntInput("Please, input desired action: ");
            if (action >= 0 && action <= maxInput) {
                return action;
            }
        } while (true);
    }

}
