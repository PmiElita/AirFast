package com.pylypchak.airfast.statements;

public class AirportSQLStatements {
	public static final String INSERT_AIRPORT_SQL = "INSERT INTO airport (name, city_id) VALUES (?,?)";
	public static final String SELECT_AIRPORTS_BY_CITY_ID_SQL ="SELECT * FROM airport WHERE city_id=?";
	public static final String SELECT_AIRPORT_BY_ID_SQL = "SELECT * FROM airport WHERE id=?";
	public static final String CALL_SELECT_AIRPORT_BY_NAMES_SQL="CALL GetAirportByCountryCityArportNames(?,?,?)";
	public static final String SELECT_ALL_AIRPORTS_SQL= "SELECT* FROM airport";
	public static final String UPDATE_AIRPORT_SQL = "UPDATE airport SET city_id=?, name = ? WHERE id =?";
	
	
	public static final String[] INSERT_AIRPORT_SQL_ORDER = { "name", "city_id" };
	public static final String[] SELECT_AIRPORTS_BY_CITY_ID_SQL_ORDER ={"city_id"};
	public static final String[] SELECT_AIRPORT_BY_ID_SQL_ORDER ={"id"};
	public static final String[] UPDATE_AIRPORT_SQL_ORDER = {"city_id", "name", "id"};
}
