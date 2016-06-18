package com.marketlogistic.bookIt.model;

import org.joda.time.DateTime;

/**
 * 
 * @author wamalalawrence 
 * Model class representing a booking request
 * 
 */
public class Booking implements Comparable<Booking>{

	private DateTime submissionTime;
	private Employee employee;
	private DateTime startTime;
	private Integer duration;

	/**
	 * This function provides custom sorting capabilities as required.
	 * Sorting is done according to booking start date-time
	 */
	@Override
	public int compareTo(Booking object) {
		Booking bookingObj = (Booking) object;
		return this.startTime.compareTo(bookingObj.getStartTime());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((submissionTime == null) ? 0 : submissionTime.hashCode());
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
		Booking other = (Booking) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (submissionTime == null) {
			if (other.submissionTime != null)
				return false;
		} else if (!submissionTime.equals(other.submissionTime))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Booking [submissionTime=" + submissionTime + ", employee=" + employee + ", startTime=" + startTime
				+ ", duration=" + duration + "]";
	}
	
	public DateTime getSubmissionTime() {
		return submissionTime;
	}


	public void setSubmissionTime(DateTime submissionTime) {
		this.submissionTime = submissionTime;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public DateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}


	public Integer getDuration() {
		return duration;
	}


	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
