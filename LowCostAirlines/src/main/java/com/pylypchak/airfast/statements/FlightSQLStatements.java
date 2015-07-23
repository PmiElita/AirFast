package com.pylypchak.airfast.statements;

public class FlightSQLStatements {
	public static final String INSERT_FLIGHT_SQL = "INSERT INTO flight (admin_id, from_airport_id, to_airport_id, min_price, max_price, hot_price, is_hot, seats, free_seats, date_from, date_to, prime_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_FLIGHT_BY_ID= "SELECT * FROM flight WHERE id= ?";
	public static final String SELECT_FLIGHTS_BY_FROM_AIRPORT_ID_SQL ="SELECT * FROM flight WHERE from_airport_id=?";
    public static final String SELECT_FLIGHTS_BY_TO_AIRPORT_ID_SQL ="SELECT * FROM flight WHERE to_airport_id=?";
    public static final String SELECT_FLIGHTS_BY_ADMIN_ID_SQL ="SELECT * FROM flight WHERE admin_id=?";
    public static final String SELECT_HOT_FLIGHTS_SQL="SELECT * FROM flight WHERE is_hot=? AND date_from > NOW() AND free_seats>0";
	public static final String CALL_GET_FLIGHTS_BY_FROM_AIRPORT_ID_DATE_FROM_SQL="CALL getFlightsByFromAirportIdAndDateFrom(?,?)"; 
    public static final String CALL_GET_FLIGHTS_BY_TO_AIRPORT_ID_DATE_FROM_SQL="CALL getFlightsByToAirportIdAndDateFrom(?,?)"; 
    public static final String CALL_GET_FLIGHTS_BY_FROM_CITY_ID_DATE_FROM_SQL="CALL getFlightsByFromCityIdAndDateFrom(?,?)"; 
    public static final String CALL_GET_FLIGHTS_BY_TO_CITY_ID_DATE_FROM_SQL="CALL getFlightsByToCityIdAndDateFrom(?,?)"; 
    public static final String CALL_GET_FLIGHTS_BY_FROM_COUNTRY_ID_DATE_FROM_SQL="CALL getFlightsByFromCountryIdAndDateFrom(?,?)"; 
    public static final String CALL_GET_FLIGHTS_BY_TO_COUNTRY_ID_DATE_FROM_SQL="CALL getFlightsByToCountryIdAndDateFrom(?,?)"; 
    public static final String UPDATE_FLIGHT_BY_ID_SQL="UPDATE flight SET admin_id=?, from_airport_id=?, to_airport_id=?, min_price=?, max_price=?, hot_price=?, is_hot=?, seats=?, free_seats=? , date_from=?, date_to=?, prime_price=? WHERE id =?";
	
    public static final String[] INSERT_FLIGHT_SQL_ORDER = { "admin_id",
			"from_airport_id", "to_airport_id", "min_price", "max_price",
			"hot_price", "is_hot", "seats", "free_seats", "date_from",
			"date_to", "prime_price" };
	 public static final String[] SELECT_FLIGHTS_BY_FROM_AIRPORT_ID_SQL_ORDER ={"from_airport_id"};
	 public static final String[] SELECT_FLIGHTS_BY_TO_AIRPORT_ID_SQL_ORDER ={"to_airport_id"};
	 public static final String[] SELECT_FLIGHTS_BY_ADMIN_ID_SQL_ORDER ={"admin_id"};
	 public static final String[] SELECT_HOT_FLIGHTS_SQL_ORDER ={"is_hot"};
	 public static final String[] CALL_GET_FLIGHTS_BY_FROM_AIRPORT_ID_DATE_FROM_SQL_ORDER={"from_airport_id", "date_from"};
	 public static final String[] CALL_GET_FLIGHTS_BY_TO_AIRPORT_ID_DATE_FROM_SQL_ORDER={"to_airport_id", "date_from"};
	 public static final String[] SELECT_FLIGHT_BY_ID_ORDER= {"id"};
	 public static final String[] UPDATE_FLIGHT_BY_ID_SQL_ORDER={ "admin_id",
			"from_airport_id", "to_airport_id", "min_price", "max_price",
			"hot_price", "is_hot", "seats", "free_seats", "date_from",
			"date_to", "prime_price","id" };
}
