package event_gateway;

import _start.GeneralPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetEvents implements iGetEvents {
    private final GeneralPresenter presenter = new GeneralPresenter();
    BufferedReader bufferedReader;

    /**
     * Returns a list of information of events read from the file.
     *
     * @return a list of information of events read from the file.
     */
    @Override
    public ArrayList<String> read() {
        return grabEventInfo();
    }

    // helper method
    private ArrayList<String> grabEventInfo() {
        ArrayList<String> allEventsInfo = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getResourceAsStream("events.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                allEventsInfo.add(currentLine);
            }
        } catch (IOException e) {
            presenter.printWrong();
        }
        return allEventsInfo;
    }
}
