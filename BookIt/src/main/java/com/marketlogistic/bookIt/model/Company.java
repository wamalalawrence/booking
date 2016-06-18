package com.marketlogistic.bookIt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.joda.time.DateTime;

/**
 * 
 * @author wamalalawrence
 * Model representation of a company
 *
 */
public class Company implements Serializable{

	private static final long serialVersionUID = 7341255566139662402L;
	private String name;
	private DateTime officeStartTime;
	private DateTime officeEndTime;
	private List<Employee> employees = new ArrayList<Employee>();
	
	//sorting and uniqueness guaranteed :)
	private SortedSet<Booking> bookings = new TreeSet<Booking>();
	
	/**
	 * 
	 * @param booking
	 * An efficient way to add bookings without use 'getBookings.add()' which wastefully gets the whole collection of bookings
	 */
	public void addBooking(Booking booking){
		this.bookings.add(booking);
	}
	
	/**
	 * 
	 * @param booking
	 * An efficient way to add employees without invoking the whole collection of employees
	 */
	public void addEmployee(Employee employee){
		this.employees.add(employee);
	}
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", officeStartTime=" + officeStartTime + ", officeEndTime=" + officeEndTime + "]";
	}
	
	//TODO - there is no real need for hashCode and equals methods for this object, you may add them if you wish :)
	
	public String getName() {
		return name;
	}
	
	public SortedSet<Booking> getBookings() {
		return bookings;
	}
	
	public void setBookings(SortedSet<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DateTime getOfficeStartTime() {
		return officeStartTime;
	}
	
	public void setOfficeStartTime(DateTime officeStartTime) {
		this.officeStartTime = officeStartTime;
	}
	
	public DateTime getOfficeEndTime() {
		return officeEndTime;
	}
	
	public void setOfficeEndTime(DateTime officeEndTime) {
		this.officeEndTime = officeEndTime;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployee(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
