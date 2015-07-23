package com.pylypchak.airfast.searcher;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.pylypchak.airfast.model.Flight;

public class Searcher {
	public static List<Flight> shearchFlight(Integer airportFromId,
			Integer cityFromId, Integer countryFromId, Integer airportToId,
			Integer cityToId, Integer countryToId, Timestamp date) throws ReflectiveOperationException, SQLException {
          List<Flight> result=null;
          FlightSearcher fromFlightsSearcher=null;
          FlightSearcher toFlightsSearcher=null;
          if (airportFromId!=null){
        	  fromFlightsSearcher = new ByAirportFromFlightSearcher(airportFromId, date);
          } else if(cityFromId != null){
        	  fromFlightsSearcher = new ByCityFromFlightSearcher(cityFromId, date);
        	  
          } else if (countryFromId !=null){
        	  fromFlightsSearcher = new ByCountryFromFlightSearcher(countryFromId, date);
          }
          
          if (airportToId!=null){
        	  toFlightsSearcher = new ByAirportToFlightSearcher(airportToId, date);
          } else if(cityToId != null){
        	  toFlightsSearcher = new ByCityToFlightSearcher(cityToId, date);
        	  
          } else if(countryToId!=null){
        	  toFlightsSearcher = new ByCountryToFlightSearcher(countryToId, date);
          }
          List<Flight> fromFlights=null;
          List<Flight> toFlights=null;
          if (fromFlightsSearcher!=null){
          fromFlights=fromFlightsSearcher.search();
          result=fromFlights;
	       }
           if (toFlightsSearcher !=null){
        	   toFlights=toFlightsSearcher.search();
        	   result=toFlights;
           }
           if (fromFlights!=null && toFlights !=null){
          result =listIntersection(fromFlights, toFlights);
           }
          return result;
	}
	
	private static List<Flight> listIntersection(List<Flight> first, List<Flight> second){
		List<Flight> result= new ArrayList<Flight>();
		for (Flight f : first){
			if (second.contains(f)){
				result.add(f);
			}
		}
		return result;
	}
}
