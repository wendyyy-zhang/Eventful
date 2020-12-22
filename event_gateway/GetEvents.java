package event_gateway;

import _start.GeneralPresenter;

import java.io.*;
import java.util.ArrayList;

public class GetEvents implements iGetEvents {
    private final GeneralPresenter presenter = new GeneralPresenter();
    BufferedReader bufferedReader;

    /**
     * Reads specific file to get the information of events. Then return the array that stores the information.
     *
     * @return ArrayList that stores the information of events.
     */
    @Override
    public ArrayList<String> read() {
        return grabEventInfo();
    }

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
