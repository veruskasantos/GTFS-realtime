package mestrado.GTFSRealTime;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;

public class GtfsRealtimeExample {

	private static String TRIP_UPDATE_URL = "https://cdn.mbta.com/realtime/TripUpdates.pb";
	private static String ALERTS_URL = "https://cdn.mbta.com/realtime/Alerts.pb";
	private static String VEHICLE_POSITIONS_URL = "https://cdn.mbta.com/realtime/VehiclePositions.pb";
	
	public static void main(String[] args) throws Exception {
		
		checkVehiclesPositions();
		//checkAlerts();
		//checkTripUpdate();
	}
	
	public static void checkTripUpdate() {
		URL url;
		try {
			url = new URL(TRIP_UPDATE_URL);
			FeedMessage feed = FeedMessage.parseFrom(url.openStream());
			for (FeedEntity entity : feed.getEntityList()) {
				if (entity.hasTripUpdate()) {
					System.out.println(entity.getTripUpdate());
					System.out.println(entity.getTripUpdate().hasVehicle());
					break;//TODO remover
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("URL problem: " +  e.getMessage());
		} catch (IOException e) {
			System.err.println("Reading problem: " +  e.getMessage());
		}	
	}
	
	public static void checkAlerts() {
		URL url;
		try {
			url = new URL(ALERTS_URL);
			FeedMessage feed = FeedMessage.parseFrom(url.openStream());
			for (FeedEntity entity : feed.getEntityList()) {
				if (entity.hasAlert()) {
					System.out.println(entity.getAlert());
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("URL problem: " +  e.getMessage());
		} catch (IOException e) {
			System.err.println("Reading problem: " +  e.getMessage());
		}	
	}
	
	public static void checkVehiclesPositions() {
		URL url;
		try {
			url = new URL(VEHICLE_POSITIONS_URL);
			FeedMessage feed = FeedMessage.parseFrom(url.openStream());
			for (FeedEntity entity : feed.getEntityList()) {
				if (entity.hasVehicle()) {
					System.out.println(entity.getVehicle());
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("URL problem: " +  e.getMessage());
		} catch (IOException e) {
			System.err.println("Reading problem: " +  e.getMessage());
		}	
	}
}
