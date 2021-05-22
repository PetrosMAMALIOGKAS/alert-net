package com.simplon.safety.alertsnet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestation {

	public String address;
	public String station;

	public Firestation() {}
	
	public Firestation(FirestationBuilder builder) {
		
		this.address = builder.address;
		this.station = builder.station;
	}
	
	public String getAddress() {
		
		return address;
	}

	public void setAddress(String address) {
		
		this.address = address;
	}

	public String getStation() {
		
		return station;
	}

	public void setStation(String station) {
		
		this.station = station;
	}

	@Override
	public String toString() {
		
		return "Firestation [address=" + address + ", station=" + station + "]";
	}
	
	
	public static class FirestationBuilder {
		
		private String address;
		private String station;
		
		public FirestationBuilder() {}

		public FirestationBuilder address(@JsonProperty("address") String address) {
			
			this.address = address;
			return this;
		}

		public FirestationBuilder station(@JsonProperty("station") String station) {
			
			this.station = station;
			return this;
		}

		public Firestation build() {
			
			return new Firestation(this);
		}
	}

}
