package museomanagment.model;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class MuseoManager implements MuseoManagement {

    private DataBaseManager db;

    /**
     * MuseoManager constructor.
     */
    public MuseoManager() {
        this.db = new DataBaseManagerImpl("jdbc:mysql://localhost:3306/museo2", "root", "");
    }

    /**
     * To be called to end the application.
     */
    public void end() {
        this.db.close();
    }

    @Override
    public void clientSubscription(final String email, final String pw, final String name, final String lastname) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getUserTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getPeriods() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getAreas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getConductors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getLanguages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void documentRegistration(final String id, final String issuingDate, final String user, final String userType) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<List<String>> getUserTicketHistory(final String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void userTypePromotionRegistration(final String name, final String discount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void ticketsNumberPromotionRegistration(final String name, final String discount, final String number) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<List<String>> checkAvailablePromotions(final String userId, final String purchaseDate, final String tourDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tourStandardRegistration(final String number, final String price) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tourRegistration(final String date, final String startTime, final String endTime, final String tourStandardID,
            final Optional<String> guide, final Optional<String> language) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<List<String>> tourHistory(final String startDate, final String endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void ticketRegistration(final String ticketsNumber, final String userId, final String purchaseDate, final String startDate,
            final String endDate, final Optional<String> conductor, final Optional<String> userPromotion,
            final Optional<String> cumulativePromotion, final String tourStandard, final boolean cumulative) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<List<String>> usersExpenseAvg() {
        // TODO Auto-generated method stub
        return null;
    }

}
