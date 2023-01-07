package museomanagment.model;

/**
 * enum of operatoins.
 */
public enum Operation {

    /**
     * 
     */
    USER_INSERT("INSERT INTO "
            + "`utenti`(`e-mail`, `password`, `nome`, `cognome`)"
            + "VALUES "
            + "(?, ?, ?, ?);"),

    /**
     * 
     */
    USERS_SELECT("SELECT `utenti`.`codice` FROM `utenti` WHERE 1;"),

    /**
     * 
     */
    USERTYPES_SELECT("SELECT `tipologie_utenti`.`codice` FROM `tipologie_utenti` WHERE 1;"),

    /**
     * 
     */
    PERIODS_SELECT("SELECT * FROM `periodi` WHERE 1;"),

    /**
     * 
     */
    AREAS_SELECT("SELECT `aree`.`codice` FROM `aree` WHERE 1;"),

    /**
     * 
     */
    CONDUCTORS_SELECT("SELECT `guide`.`codice` FROM `guide` WHERE 1;"),

    /**
     * 
     */
    LANGUAGES_SELECT("SELECT `lingue`.`codice` FROM `lingue` WHERE 1;"),

    /**
     * 
     */
    DOCUMENT_INSERT("INSERT INTO "
            + "`documenti`(`numeroDocumento`, `dataEmissione`, `dataScadenza`, `codiceUtente`) "
            + "VALUES "
            + "(?, ?, ?, ?);"),

    /**
     * 
     */
    DOCUMENT_USER_SELECT("SELECT * FROM `appartenenze` WHERE `appartenenze`.`codiceTipologiaUtente` = ? "
            + "AND `appartenenze`.`codiceUtente` = ?"),

    /**
     * 
     */
    DOCUMENT_USER_CORRELATION("INSERT INTO "
            + "`appartenenze`(`codiceTipologiaUtente`, `codiceUtente`) "
            + "VALUES "
            + "(?, ?);"),

    /**
     * 
     */
    SALE_HISTORY("SELECT "
            + "* "
            + "FROM "
            + "`vendite` "
            + "WHERE "
            + "`vendite`.`codiceUtente` = ?;"),

    /**
     * 
     */
    P_USER_INSERT("INSERT INTO "
            + "`promozioni_utenti`(`nome`, `sconto`) "
            + "VALUES "
            + "(?,?)"),

    /**
     * 
     */
    P_USER_ACTIVITY("INSERT INTO "
            + "`attivita_promozioni_utenti`(`codicePromozione`, `dataInizio`, `dataFine`) "
            + "VALUES "
            + "(?,?,?);"),

    /**
     * 
     */
    P_USER_AVAILABILITY("INSERT INTO "
            + "`valenze_promozioni_utenti`(`codicePromozione`, `dataInizio`, `dataFine`) "
            + "VALUES "
            + "(?,?,?);"),

    /**
     * 
     */
    PROMOTION_USERTYPE_CORRELATION("INSERT INTO"
            + "`benefici`(`codiceTipologia`, `codicePromozione`) "
            + "VALUES "
            + "(?,?);"),

    /**
     * 
     */
    P_CUMULATIVE_INSERT("INSERT INTO "
            + "`p_cumulative`( `nome`, `sconto`, `numeroPosti`) "
            + "VALUES "
            + "(?,?,?);"),

    /**
     * 
     */
    P_CUMULATIVE_ACTIVITY("INSERT INTO "
            + "`attivita_promozioni_cumulative`(`codicePromozione`, `dataInizio`, `dataFine`) "
            + "VALUES "
            + "(?,?,?);"),

    /**
     * 
     */
    P_CUMULATIVE_AVAILABILITY("INSERT INTO "
            + "`valenze_promozioni_cumulative`(`codicePromozione`, `dataInizio`, `dataFine`) "
            + "VALUES "
            + "(?,?,?);"),

    /**
     * 
     */
    P_ON_USERTYPE("SELECT"
            + "PU.*"
            + "FROM"
            + "`promozioni_utenti` AS PU,"
            + "`applicabilita` AS A,"
            + "`attivita_promozioni_utenti` AS APU,"
            + "`valenze_promozioni_utenti` AS VPU"
            + "WHERE"
            + "`A`.`codiceUtente` = ? AND"
            + "`APU`.`codicePromozione` = `A`.`codicePromozioneUtente` AND"
            + "`VPU`.`codicePromozione` = `A`.`codicePromozioneUtente` AND"
            + "? >= `VPU`.`dataInizio` AND"
            + "? <= `VPU`.`dataFine` AND"
            + "? >= `APU`.`dataInizio` AND"
            + "? <= `APU`.`dataFine` AND"
            + "`VPU`.`codicePromozione` = `PU`.`codice` AND"
            + "`APU`.`codicePromozione` = `PU`.`codice`;"),

    /**
     * 
     */
    P_ON_SALENUMBER("SELECT"
            + "`p_cumulative`.*"
            + "FROM"
            + "`p_cumulative`,"
            + "`valenze_promozioni_cumulative`,"
            + "`attivita_promozioni_cumulative`"
            + "WHERE"
            + "`p_cumulative`.`codice` = `valenze_promozioni_cumulative`.`codicePromozione`"
            + "AND `p_cumulative`.codice = `attivita_promozioni_cumulative`.`codicePromozione`"
            + "AND ? >= `valenze_promozioni_cumulative`.`dataInizio`"
            + "AND ? <= `valenze_promozioni_cumulative`.`dataFine`"
            + "AND ? >= `attivita_promozioni_cumulative`.`dataInizio`"
            + "AND ? <= `attivita_promozioni_cumulative`.dataFine;"),

    /**
     * 
     */
    TOUR_STANDARD_INSERT("INSERT INTO "
            + "`tour_standard`( `numeroPosti`, `prezzo`) VALUES (?, ?)"),
    /**
     * 
     */
    T_STANDARD_AREAS_CORRELATION("INSERT INTO "
            + "`locazioni`(`codiceArea`, `codiceTour`) VALUES (?,?);"),

    /**
     * 
     */
    AUTONOMOUS_T_INSERT("INSERT INTO "
            + "`tour_autonomi`(`data`, `oraInizio`, `oraFine`, `codiceTourStandard`) "
            + "VALUES "
            + "(?,?,?,?);"),
    /**
     * 
     */
    AUTONOMOUS_T_SELECT("SELECT * FROM `tour_autonomi` WHERE `data` = ? AND  `oraInizio` = ? AND "
            + "`oraFine` = ?  AND `codiceTourStandard` = ? "),

    /**
     * 
     */
    GUIDED_T_SELECT("SELECT * FROM `tour_guidati` WHERE `data` = ? AND  `oraInizio` = ? AND "
            + "`oraFine` = ?  AND `codiceGuida` = ? "),

    /**
     * 
     */
    GUIDED_T_INSERT("INSERT INTO "
            + "`tour_guidati`(`data`, `oraInizio`, `oraFine`, `codiceGuida`, `codiceLingua`, `codiceTourStandard`) "
            + "VALUES "
            + "(?,?,?,?,?,?);"),

    /**
     * 
     */
    TOUR_IN_A_PERIOD("SELECT "
            + "`tour_guidati`.`data`, "
            + "`tour_guidati`.`oraInizio`, "
            + "`tour_guidati`.`oraFine`, "
            + "`tour_guidati`.`codiceGuida`, "
            + "`tour_guidati`.`codiceLingua`, "
            + "`tour_guidati`.`codiceTourStandard` "
            + "FROM "
            + "`tour_guidati` "
            + "WHERE "
            + "`tour_guidati`.`data` <= ? AND `tour_guidati`.`data` >= ?;"),

    /**
     * 
     */
    SALE_INSERT("INSERT INTO "
            + "`vendite`(`dataAcquisto`, `oraAcquisto`, `numeroPosti`, `codiceUtente`) VALUES "
            + "(?,?,?,?);"),

    /**
     * 
     */
    P_USER_APPLY("INSERT INTO "
            + "`applicazioni_promozioni_utenti`(`codiceVendita`, `codicePromozioneUtente`) VALUES "
            + "(?,?);"),

    /**
     * 
     */
    AUTONOMOUS_TOUR_CORRELATION("INSERT INTO "
            + "`corrispondenze_tour_autonomi`(`codiceVendita`, `data`, `oraInizio`, `oraFine`, `codiceTourStandard`) "
            + "VALUES "
            + "(?,?,?,?,?);"),

    /**
     * 
     */
    GUIDED_TOUR_CORRELATION("INSERT INTO "
            + "`corrispondenze_tour_guidati`(`codiceVendita`, `data`, `oraInizio`, `oraFine`, `codiceGuida`) "
            + "VALUES "
            + "(?,?,?,?,?);"),

    /**
     * 
     */
    T_AUTONOMOUS_EXPENSE_AVG("SELECT `utenti`.*,"
            + "AVG(((ve2.`numeroPosti` * `tour_standard`.`prezzo`) - (COALESCE("
            + "(SELECT"
            + "(SELECT pu.sconto"
            + "FROM "
            + "`promozioni_utenti` pu"
            + "JOIN `applicazioni_promozioni_utenti` apu ON (pu.codice = apu.codicePromozioneUtente)"
            + "JOIN `vendite` ve ON (apu.codiceVendita = ve.codice)"
            + "WHERE "
            + "ve.codice = ve2.codice"
            + "UNION "
            + "SELECT"
            + "pc.sconto"
            + "FROM "
            + "`p_cumulative` pc JOIN `applicazioni_promozioni_cumulative` apc ON (pc.codice = apc.codicePromozioneCumulative)"
            + "JOIN `vendite` ve1 ON (apc.codiceVendita = ve1.codice)"
            + "WHERE "
            + "ve1.codice = ve2.codice "
            + ") AS sconto"
            + "WHERE 1 "
            + "), 0) / 100) * (ve2.`numeroPosti` * `tour_standard`.`prezzo`))) AS spesa_media"
            + "FROM "
            + "`utenti` JOIN `vendite` ve2 ON (ve2.`codiceUtente` = `utenti`.`codice`)"
            + "JOIN `corrispondenze_tour_autonomi` ON (`corrispondenze_tour_autonomi`.`codiceVendita` = ve2.`codice`)"
            + "JOIN `tour_standard` ON (`tour_standard`.`codice` = `corrispondenze_tour_autonomi`.`codiceTourStandard`)"
            + "WHERE "
            + "ve2.dataAcquisto >= \"?\" AND "
            + "ve2.dataAcquisto <= \"?\""
            + "GROUP BY "
            + "`utenti`.`codice`;"),

    /**
     * 
     */
    GUIDED_TOUR_EXPENSE_AVG("SELECT"
            + "`utenti`.*,"
            + "AVG((`V`.`numeroPosti` *  `tour_standard`.`prezzo`) -"
            + "                IFNULL((`V`.`numeroPosti` *  `tour_standard`.`prezzo` * `PC`.`sconto` / 100), 0) -"
            + "                IFNULL((`tour_standard`.`prezzo` * `PU`.`sconto` / 100), 0))"
            + "        AS spesa_media"
            + "FROM"
            + "`utenti` JOIN `vendite` AS V ON (`V`.`codiceUtente` = `utenti`.`codice`)"
            + "        JOIN `corrispondenze_tour_guidati` ON (`corrispondenze_tour_guidati`.`codiceVendita` = `V`.`codice` )"
            + "        JOIN `tour_guidati` ON ("
            + "                `corrispondenze_tour_guidati`.`data` = `tour_guidati`.`data` AND"
            + "                `corrispondenze_tour_guidati`.`oraInizio` = `tour_guidati`.`oraInizio` AND"
            + "                `corrispondenze_tour_guidati`.`oraFine` = `tour_guidati`.`oraFine`"
            + "        )"
            + "        JOIN `tour_standard` ON ("
            + "                `tour_standard`.`codice` = `tour_guidati`.`codiceTourStandard`"
            + "        )"
            + "        LEFT OUTER JOIN `applicazioni_promozioni_cumulative` AS APC ON ("
            + "`APC`.`codiceVendita` = `V`.`codice`"
            + ")"
            + "        LEFT OUTER JOIN `p_cumulative` AS PC ON ("
            + "`PC`.`codice` = `APC`.`codicePromozioneCumulative` AND `V`.`numeroPosti` = `PC`.`numeroPosti`"
            + ")"
            + "        LEFT OUTER JOIN `applicazioni_promozioni_utenti` AS APU ON ("
            + "`APU`.`codiceVendita` = `V`.`codice`"
            + ")"
            + "        LEFT OUTER JOIN `promozioni_utenti` AS PU ON ("
            + "`PU`.`codice` = `APU`.`codicePromozioneUtente`"
            + ")"
            + "WHERE"
            + "        `V`.`dataAcquisto` <= ? AND"
            + "        `V`.`dataAcquisto` >= ?"
            + "GROUP BY"
            + "    `utenti`.`codice`;");

    private final String query;

    Operation(final String query) {
        this.query = query;
    }

    /**
     * @return query.
     */
    public String getQuery() {
        return this.query;
    }
}
