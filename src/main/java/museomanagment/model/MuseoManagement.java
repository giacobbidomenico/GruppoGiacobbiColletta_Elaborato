package museomanagment.model;

import java.util.List;
import java.util.Optional;

/**
 * Models a museum management which manages every operation needed.
 */
public interface MuseoManagement {

    /**
     * Subscribe a new client.
     * @param email
     * @param pw
     * @param name
     * @param lastname
     */
    void clientSubscription(String email, String pw, String name, String lastname);

    /**
     * @return a list of ids of registered users
     */
    List<String> getUsers();

    /**
     * @return a list of ids of user types.
     */
    List<String> getUserTypes();

    /**
     * @return a list of strings that resemble to a time interval (date - date).
     */
    List<String> getPeriods();

    /**
     * @return a list of all the areas the museum is composed.
     */
    List<String> getAreas();

    /**
     * @return a list of conductors.
     */
    List<String> getConductors();

    /**
     * @return a list of languages.
     */
    List<String> getLanguages();

    /**
     * Registers a new document and associates the specified type to the user.
     * @param id
     * @param issuingDate
     * @param expireDate
     * @param user
     * @param userType
     */
    void documentRegistration(String id, String issuingDate, String expireDate, String user, String userType);

    /**
     * @param userId the id of the user
     * @return a list of tickets a user bought
     */
    List<List<String>> getUserTicketHistory(String userId);

    /**
     * Registers a new promotion based on user types.
     * @param name of the promotion
     * @param discount
     * @param activityIndex
     * @param availabilityIndex
     * @param userType
     */
    void userTypePromotionRegistration(String name, String discount, int activityIndex, int availabilityIndex, String userType);

    /**
     * Registers a new promotion based on the number of tickets bought.
     * @param name of the promotion
     * @param number of tickets bought
     * @param discount
     */
    void ticketsNumberPromotionRegistration(String name, String discount, String number);

    /**
     * Checks which promotions are available for a given tour.
     * @param userId
     * @param purchaseDate
     * @param tourDate
     * @return a list of promotions.
     */
    List<List<String>> checkAvailablePromotions(String userId, String purchaseDate, String tourDate);

    /**
     * Registers a new tour standard.
     * @param number
     * @param price
     */
    void tourStandardRegistration(String number, String price);

    /**
     * Registers a new tour.
     * @param date
     * @param startTime
     * @param endTime
     * @param tourStandardID
     * @param guide
     * @param language
     */
    void tourRegistration(String date, String startTime, String endTime, String tourStandardID, Optional<String> guide, Optional<String> language);

    /**
     * @param startDate
     * @param endDate
     * @return a list of tours.
     */
    List<List<String>> tourHistory(String startDate, String endDate);

    /**
     * Registers a new ticket purchase.
     * @param ticketsNumber
     * @param userId
     * @param purchaseDate
     * @param startDate
     * @param endDate
     * @param conductor
     * @param userPromotion
     * @param cumulativePromotion
     * @param tourStandard
     * @param cumulative
     */
    void ticketRegistration(String ticketsNumber, String userId, String purchaseDate, String startDate, String endDate, Optional<String> conductor, Optional<String> userPromotion, Optional<String> cumulativePromotion, String tourStandard, boolean cumulative);

    /**
     * @return list of users with their average expense
     */
    List<List<String>> usersExpenseAvg();
}
