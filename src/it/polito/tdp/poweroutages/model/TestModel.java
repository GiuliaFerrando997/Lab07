package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {
		public static void main(String[] args) {
			
			Model model = new Model();
			
			List<Nerc> nercList = model.getNercList();
			System.out.println("Nerc List size: " + nercList.size());
			
			Nerc selectedNerc = nercList.get(3);
			List<PowerOutages> worstCase = model.getResult(3, 250, selectedNerc);
			
			System.out.println("Tot people affected: " + model.getPersone(worstCase));
			System.out.println("Tot hours of outage: " + model.getOre(worstCase));
			
			for(PowerOutages p : worstCase) {
			System.out.println(p.toString());
			}
			
		}
}
