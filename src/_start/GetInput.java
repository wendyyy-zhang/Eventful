package _start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInput {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final GeneralPresenter presenter = new GeneralPresenter();

    /**
     * Returns a non-empty string representing the user input.
     *
     * @return a non-empty string representing the user input
     */
    public String getUserInput() {
        String input = "";
        try {
            input = bufferedReader.readLine();
            Pattern pattern = Pattern.compile("^\\s*$");
            Matcher matcher = pattern.matcher(input);

            while (matcher.matches()) {  // avoid empty input
                presenter.printEnterSomething();
                input = bufferedReader.readLine();
                matcher = pattern.matcher(input);
            }

        } catch (IOException e) {
            presenter.printWrong();
        }
        return input;
    }

    /**
     * Returns the string version of a number in the given valid range or keyword "back".
     *
     * @param validRange the given valid range
     * @return the string version of a number in the given valid range or keyword "back"
     */
    public String getNumericalInput(int validRange) {

        String input = "";
        try {
            input = bufferedReader.readLine();

            if (!input.equals("exit")) {
                input = validInput(input, validRange);
            } else {
                System.exit(0);
            }
        } catch (IOException e) {
            presenter.printWrong();
        }
        return input;

    }

    /**
     * Returns a number in the given valid range.
     *
     * @param validRange the range a valid number should be in
     * @return a number in the given valid range
     */
    public int getNumber(int validRange) {
        String input = getNumericalInput(validRange);
        while (input.equals("back")) {
            presenter.printEnterNumber();
            input = getNumericalInput(validRange);
        }
        return Integer.parseInt(input);
    }

    // helper methods
    private String validInput(String input, int validRange) {
        try {
            boolean isNotNumber = notNumber(input, validRange);
            boolean isNotBack = !input.equals("back");
            boolean isNotExit = !input.equals("exit");
            while (isNotNumber && isNotBack && isNotExit) {
                presenter.printEnterNumber();
                input = bufferedReader.readLine();
                isNotNumber = notNumber(input, validRange);
                isNotBack = !input.equals("back");
                isNotExit = !input.equals("exit");
            }
        } catch (IOException e) {
            presenter.printWrong();
        }
        return input;
    }

    private boolean notNumber(String input, int validRange) {
        for (int i = 1; i <= validRange; i++) {
            if (String.valueOf(i).equals(input)) {
                return false;
            }
        }
        return true;
    }

}
