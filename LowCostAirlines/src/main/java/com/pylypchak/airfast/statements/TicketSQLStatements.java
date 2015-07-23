package com.pylypchak.airfast.statements;

public class TicketSQLStatements {
	public static final String INSERT_TICKET_SQL ="INSERT INTO ticket (flight_id, user_id, price, status, date, bagage_quantity, is_prime_registration, is_prime_boarding) VALUES (?,?,?,?,?,?,?,?)";
	public static final String SELECT_TICKETS_BY_USER_ID_SQL = "SELECT * FROM ticket WHERE user_id=?";
	public static final String SELECT_TICKET_BY_ID_SQL ="SELECT * FROM ticket WHERE id = ?"; 
	public static final String UPDATE_TICKET_BY_ID_SQL="UPDATE ticket SET flight_id =?, user_id =?, price=?, status=?, date=?, bagage_quantity=?, is_prime_registration=?, is_prime_boarding=? WHERE id=?";
	
	
	public static final String[] INSERT_TICKET_SQL_ORDER = {"flight_id","user_id","price","status", "date","bagage_quantity","is_prime_registration", "is_prime_boarding" };
	public static final String[] SELECT_TICKETS_BY_USER_ID_SQL_ORDER ={"user_id"};
	public static final String[] SELECT_TICKET_BY_ID_SQL_ORDER ={"id"};
	public static final String[] UPDATE_TICKET_BY_ID_SQL_ORDER ={"flight_id","user_id", "price", "status","date", "bagage_quantity", "is_prime_registration", "is_prime_boarding", "id"};
}
