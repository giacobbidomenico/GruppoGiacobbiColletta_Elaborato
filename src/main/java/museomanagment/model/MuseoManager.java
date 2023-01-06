package museomanagment.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class MuseoManager implements MuseoManagement {

    private final DataBaseManager db;

    /**
     * MuseoManager constructor.
     */
    public MuseoManager() {
        this.db = new DataBaseManagerImpl("jdbc:mysql://localhost:3306/museo2","root","");
    }

    /**
     * To be called to end the application.
     */
    public void end() {
        this.db.close();
    }

    @Override
    public void clientSubscription(final String email, final String pw, final String name, final String lastname) {
        db.setQuery(Operation.USER_INSERT.getQuery());
        db.addParameter(email);
        db.addParameter(pw);
        db.addParameter(name);
        db.addParameter(lastname);
        db.executeQuery();
    }


    @Override
    public List<String> getUsers() {
        db.setQuery(Operation.USERS_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<String> users = new ArrayList<>();
        try {
            while (rs.next()) {
                users.add(rs.getString(1));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return users;
    }

    @Override
    public List<String> getUserTypes() {
        db.setQuery(Operation.USERTYPES_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<String> userTypes = new ArrayList<>();
        try {
            while (rs.next()) {
                userTypes.add(rs.getString(1));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return userTypes;
    }

    @Override
    public List<List<String>> getPeriods() {
//        periods.add(IntStream.range(1, n).boxed().map(x -> rs.getString(x)).collect(Collectors.toList()));
        db.setQuery(Operation.PERIODS_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<List<String>> periods = new ArrayList<>();

        try {
            final int n = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                periods.add(List.of(rs.getString(1),rs.getString(2)));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return periods;
    }

    @Override
    public List<String> getAreas() {
        db.setQuery(Operation.AREAS_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<String> areas = new ArrayList<>();
        try {
            while (rs.next()) {
                areas.add(rs.getString(1));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return areas;
    }

    @Override
    public List<String> getConductors() {
        db.setQuery(Operation.CONDUCTORS_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<String> conductors = new ArrayList<>();
        try {
            while (rs.next()) {
                conductors.add(rs.getString(1));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return conductors;
    }

    @Override
    public List<String> getLanguages() {
        db.setQuery(Operation.CONDUCTORS_SELECT.getQuery());
        final ResultSet rs = db.executeQuery().get();
        final List<String> conductors = new ArrayList<>();
        try {
            while (rs.next()) {
                conductors.add(rs.getString(1));
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return conductors;
    }

    @Override
    public void documentRegistration(final String id, final String issuingDate, final String expireDate, final String user, final String userType) {
        db.setQuery(Operation.DOCUMENT_INSERT.getQuery());
        db.addParameter(id);
        db.addParameter(issuingDate);
        db.addParameter(expireDate);
        db.addParameter(user);
        db.executeQuery();
        db.setQuery(Operation.DOCUMENT_USER_CORRELATION.getQuery());
        db.addParameter(userType);
        db.addParameter(user);
        db.executeQuery();
    }

    @Override
    public List<List<String>> getUserTicketHistory(final String userId) {
      db.setQuery(Operation.SALE_HISTORY.getQuery());
      db.addParameter(userId);
      final ResultSet rs = db.executeQuery().get();
      final List<List<String>> history = new ArrayList<>();

      try {
          final int n = rs.getMetaData().getColumnCount();
          while (rs.next()) {
              List<String> row = new ArrayList<>();
              for (int i = 0; i < n; i++) {
                  row.add(rs.getString(i));
              }
              history.add(row);
          }
          rs.close();
      } catch (final SQLException e) {
          throw new IllegalStateException();
      }

      return history;
    }

    @Override
    public void userTypePromotionRegistration(final String name, final String discount, int activityIndex, int availabilityIndex, String userType) {
        List<List<String>> periods = this.getPeriods();
        this.db.setQuery(Operation.P_USER_INSERT.getQuery());
        this.db.addParameter(name);
        this.db.addParameter(discount);
        ResultSet rs = this.db.executeQuery().get();
        String pId;
        try {
            rs.next();
            pId = rs.getString(1);
            rs.close();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException();
        }

        this.db.setQuery(Operation.P_USER_ACTIVITY.getQuery());
        this.db.addParameter(pId);
        this.db.addParameter(periods.get(activityIndex).get(0));
        this.db.addParameter(periods.get(activityIndex).get(1));
        this.db.executeQuery();

        this.db.setQuery(Operation.P_USER_AVAILABILITY.getQuery());
        this.db.addParameter(pId);
        this.db.addParameter(periods.get(availabilityIndex).get(0));
        this.db.addParameter(periods.get(availabilityIndex).get(1));
        this.db.executeQuery();

        this.db.setQuery(Operation.PROMOTION_USERTYPE_CORRELATION.getQuery());
        this.db.addParameter(pId);
        this.db.addParameter(userType);
        this.db.executeQuery();
    }

    @Override
    public void ticketsNumberPromotionRegistration(final String name, final String discount, int activityIndex, int availabilityIndex, String number) {
        List<List<String>> periods = this.getPeriods();
        this.db.setQuery(Operation.P_CUMULATIVE_INSERT.getQuery());
        this.db.addParameter(name);
        this.db.addParameter(discount);
        ResultSet rs = this.db.executeQuery().get();
        String pId;
        try {
            rs.next();
            pId = rs.getString(1);
            rs.close();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException();
        }

        this.db.setQuery(Operation.P_CUMULATIVE_ACTIVITY.getQuery());
        this.db.addParameter(pId);
        this.db.addParameter(periods.get(activityIndex).get(0));
        this.db.addParameter(periods.get(activityIndex).get(1));
        this.db.executeQuery();

        this.db.setQuery(Operation.P_CUMULATIVE_AVAILABILITY.getQuery());
        this.db.addParameter(pId);
        this.db.addParameter(periods.get(availabilityIndex).get(0));
        this.db.addParameter(periods.get(availabilityIndex).get(1));
        this.db.executeQuery();

    }

    @Override
    public List<List<String>> checkAvailablePromotions(final String userId, final String purchaseDate, final String tourDate) {
        this.db.setQuery(Operation.P_ON_USERTYPE.getQuery());
        this.db.addParameter(tourDate);
        this.db.addParameter(tourDate);
        this.db.addParameter(purchaseDate);
        this.db.addParameter(purchaseDate);
        ResultSet rs = this.db.executeQuery().get();
        List<List<String>> promotions = new ArrayList<>();
        try {
            final int n = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    row.add(rs.getString(i));
                }
                promotions.add(row);
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        this.db.setQuery(Operation.P_ON_SALENUMBER.getQuery());
        this.db.addParameter(tourDate);
        this.db.addParameter(tourDate);
        this.db.addParameter(purchaseDate);
        this.db.addParameter(purchaseDate);
        rs = this.db.executeQuery().get();
        try {
            final int n = rs.getMetaData().getColumnCount() - 1;
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    row.add(rs.getString(i));
                }
                promotions.add(row);
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        return promotions;
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
