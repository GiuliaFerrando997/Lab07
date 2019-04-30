package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class PowerOutages implements Comparable<PowerOutages>{
	
	private int id;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private long oreTot;
	
	public PowerOutages(int id, int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished) {
		super();
		this.id = id;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.oreTot = dateEventBegan.until(dateEventFinished, ChronoUnit.HOURS);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	public long getOreTot() {
		return oreTot;
	}

	public void setOreTot(long oreTot) {
		this.oreTot = oreTot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PowerOutages [id=").append(id).append(", customersAffected=").append(customersAffected)
				.append(", dateEventBegan=").append(dateEventBegan).append(", dateEventFinished=")
				.append(dateEventFinished).append(", oreTot=").append(oreTot).append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(PowerOutages o) {
		return getDateEventBegan().compareTo(o.getDateEventBegan());
	}
	
	
	

}
