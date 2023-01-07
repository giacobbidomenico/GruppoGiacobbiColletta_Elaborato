package museomanagment.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
        this.db = new DataBaseManagerImpl("jdbc:mysql://localhost:3306/museo", "root", "");
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

    /**
     * Checks whether an user already belongs to a type.
     * @param user
     * @param userType
     * @return true if correlation already exists
     */
    public boolean checkUserType(final String user, final String userType) {
        db.setQuery(Operation.DOCUMENT_USER_SELECT.getQuery());
        db.addParameter(userType);
        db.addParameter(user);
        final ResultSet rs = db.executeQuery().get();
        try {
            if (rs.next()) {
                return true;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
        System.out.println("false");
        return false;
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
              for (int i = 1; i < n+1; i++) {
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
    public void userTypePromotionRegistration(final String name, final String discount, final int activityIndex, final int availabilityIndex, final String userType) {
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
        this.db.addParameter(userType);
        this.db.addParameter(pId);
        this.db.executeQuery();
    }

    @Override
    public void ticketsNumberPromotionRegistration(final String name, final String discount, final int activityIndex, final int availabilityIndex, final String number) {
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
                for (int i = 1; i < n + 1; i++) {
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
                for (int i = 1; i < n + 1; i++) {
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
    public void tourStandardRegistration(final String number, final String price, final String areaId) {
        this.db.setQuery(Operation.TOUR_STANDARD_INSERT.getQuery());
        this.db.addParameter(number);
        this.db.addParameter(price);
        ResultSet rs = this.db.executeQuery().get();
        String tourId;
        try {
            rs.next();
            tourId = rs.getString(1);
            rs.close();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException();
        }

        this.db.setQuery(Operation.T_STANDARD_AREAS_CORRELATION.getQuery());
        this.db.addParameter(tourId);
        this.db.addParameter(areaId);
        this.db.executeQuery().get();
    }


    @Override
    public boolean checkGuidedTourExists(final String date, final String startTime, final String endTime, final String conductor) {
        db.setQuery(Operation.GUIDED_T_SELECT.getQuery());
        db.addParameter(date);
        db.addParameter(startTime);
        db.addParameter(endTime);
        db.addParameter(conductor);
        final ResultSet rs = db.executeQuery().get();
        try {
            if (rs.next()) {
                return false;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
        return true;
    }

    @Override
    public boolean checkAutonomousTourExists(final String date, final String startTime, final String endTime, final String tourStandard) {
        db.setQuery(Operation.AUTONOMOUS_T_SELECT.getQuery());
        db.addParameter(date);
        db.addParameter(startTime);
        db.addParameter(endTime);
        db.addParameter(tourStandard);
        final ResultSet rs = db.executeQuery().get();
        try {
            if (rs.next()) {
                return false;
            }
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
        return true;
    }

    @Override
    public void tourRegistration(final String date, final String startTime, final String endTime, final String tourStandardID,
            final Optional<String> guide, final Optional<String> language) {

        if (guide.isPresent()) {
            this.db.setQuery(Operation.GUIDED_T_INSERT.getQuery());
        } else {
            this.db.setQuery(Operation.AUTONOMOUS_T_INSERT.getQuery());
        }
        this.db.addParameter(date);
        this.db.addParameter(startTime);
        this.db.addParameter(endTime);
        if (guide.isPresent()) {
            this.db.addParameter(guide.get());
            this.db.addParameter(language.get());
        }
        this.db.addParameter(tourStandardID);
        this.db.executeQuery();
    }

    @Override
    public List<List<String>> tourHistory(final String startDate, final String endDate) {
        this.db.setQuery(Operation.TOUR_IN_A_PERIOD.getQuery());
        this.db.addParameter(startDate);
        this.db.addParameter(endDate);
        final List<List<String>> history = new ArrayList<>();
        ResultSet rs = this.db.executeQuery().get();
        try {
            final int n = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i < n + 1; i++) {
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
    public void ticketRegistration(final String ticketsNumber, final String userId, final String date, final String startTime, 
            final String endTime, final Optional<String> conductor, final String tourStandard, final boolean guided) {
        this.db.setQuery(Operation.SALE_INSERT.getQuery());
        this.db.addParameter(java.time.LocalDate.now().toString());
        this.db.addParameter(java.time.LocalTime.now().toString().substring(0, 8));
        this.db.addParameter(ticketsNumber);
        this.db.addParameter(userId);
        ResultSet rs = this.db.executeQuery().get();
        String saleId;
        try {
            rs.next();
            saleId = rs.getString(1);
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }

        this.db.setQuery(Operation.P_USER_APPLY.getQuery());
        this.db.addParameter(saleId);
        this.db.addParameter(userId);
        this.db.executeQuery();

        if (guided) {
            this.db.setQuery(Operation.GUIDED_TOUR_CORRELATION.getQuery());
        } else {
            this.db.setQuery(Operation.AUTONOMOUS_TOUR_CORRELATION.getQuery());
        }
        this.db.addParameter(saleId);
        this.db.addParameter(date);
        this.db.addParameter(startTime);
        this.db.addParameter(endTime);
        if (guided) {
            this.db.addParameter(conductor.get());
        } else {
            this.db.addParameter(tourStandard);
        }
        this.db.executeQuery();
    }

    @Override
    public List<List<String>> usersExpenseAvg() {
        this.db.setQuery(Operation.T_AUTONOMOUS_EXPENSE_AVG.getQuery());
        final List<List<String>> history = new ArrayList<>();
        ResultSet rs = this.db.executeQuery().get();
        try {
            final int n = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i < n + 1; i++) {
                    row.add(rs.getString(i));
                }
                history.add(row);
            }
            rs.close();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }


        this.db.setQuery(Operation.GUIDED_TOUR_EXPENSE_AVG.getQuery());
        rs = this.db.executeQuery().get();
        try {
            final int n = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i < n + 1; i++) {
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

}
