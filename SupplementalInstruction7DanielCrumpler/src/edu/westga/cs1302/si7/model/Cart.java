package edu.westga.cs1302.si7.model;

import java.util.ArrayList;

public class Cart implements Calcuable{
	
	ArrayList<Calcuable> objects;
	
	public Cart() {
		this.objects = new ArrayList<Calcuable>();
	}
	
	public void add(Calcuable object) {
		this.objects.add(object);
	}
	
	public ArrayList<Carpet> getCarpets() {
		ArrayList<Carpet> carpets = new ArrayList<Carpet>();
		for (Calcuable currObject : this.objects) {
			if (currObject.getClass() == Carpet.class) {
				carpets.add((Carpet) currObject);
			}
		}
		return carpets;
	}
	
	@Override
	public double calculate() {
		double totalCost = 0;
		for (Calcuable currObject : this.objects) {
			totalCost += currObject.calculate();
		}
		return totalCost;
	}

	@Override
	public String getDescription() {
		String description = "";
		for (Calcuable currObject : this.objects) {
			description += currObject.getDescription() + System.lineSeparator();
		}
		return description;
	}

}
