package rating_related;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * A rating manager.
 * Stores the relationship between organizers and their ratings.
 */
public class RatingManager {
    private final Hashtable<iRate, ArrayList<Integer>> organizerToScore;

    /**
     * Constructs a new rating manager.
     */
    public RatingManager() {
        organizerToScore = new Hashtable<>();
    }

    /**
     * Returns the list of ratings the given organizer has received.
     *
     * @param organizer the given organizer
     * @return the list of ratings the given organizer has received
     */
    public ArrayList<Integer> getScores(iRate organizer) {
        return organizerToScore.getOrDefault(organizer, new ArrayList<>());
    }

    /**
     * Update the rating of the given organizer by the given score
     *
     * @param organizer the given organizer
     * @param score     the new score given
     */
    public void updateRating(iRate organizer, int score) {
        addRating(organizer, score);
        setRating(organizer, getScores(organizer));
    }

    // helper methods
    private void addRating(iRate organizer, int score) {
        if (!getScores(organizer).isEmpty()) {
            organizerToScore.get(organizer).add(score);
        } else {
            ArrayList<Integer> scores = new ArrayList<>();
            scores.add(score);
            organizerToScore.put(organizer, scores);
        }
    }

    private void setRating(iRate organizer, ArrayList<Integer> scores) {
        if (scores.size() >= 5) {
            int score = calculateSum(scores);
            String rating = String.valueOf(score);
            organizer.setRating(rating);
        }
    }

    private int calculateSum(ArrayList<Integer> scores) {
        int average = 0;
        int length = scores.size();
        for (int score : scores) {
            average += score;
        }
        return average / length;
    }
}
