package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	private PowerOutageDAO podao;
	private Nerc zona;
	private int oreMax;
	private int anniMax;
	private List<PowerOutages> best;
	private int personeBest;
	private int totOre;
	private int totAnni;
	private List<PowerOutages> blackout;
	
	public Model() {
		podao = new PowerOutageDAO();
		this.best=new ArrayList();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutages> getResult(int anni, int ore, Nerc zona) {
		this.zona=zona;
		this.oreMax=ore;
		this.anniMax=anni;
		this.best=null;
		List<PowerOutages> parziale = new ArrayList();
		this.totAnni=0;
		this.totOre=0;
		this.personeBest=0;
		this.blackout=(List<PowerOutages>) podao.getBlack(zona);
		Collections.sort(blackout);
		this.cerca(parziale, 0);
		return best;
	}

	private void cerca(List<PowerOutages> parziale, int L) {
		if(L!=0 && this.getPersone(parziale)>=this.personeBest) {
			best=new ArrayList<PowerOutages>(parziale);
			System.out.println(best.size());
			personeBest=this.getPersone(parziale);
		}
		
		for(PowerOutages p : this.blackout) {
			//System.out.println(p.toString());
			if(L==0) {
				parziale.add(p);
				cerca(parziale, L+1);
			}
			if(!parziale.contains(p)) {
				if(!this.rispettaCondizioneAnno(parziale, p)==true) {
					parziale.remove(parziale.get(0));
				}
					if(this.rispettaCondizioneOre(parziale, p)==true) {
						//System.out.println(p.getOreTot()+" "+this.getOre(parziale));
						parziale.add(p);
						cerca(parziale, L+1);
						parziale.remove(p);
					}
				
			}
			
		}
		
	}

	private boolean rispettaCondizioneAnno(List<PowerOutages> parziale, PowerOutages p) {
		if((p.getDateEventFinished().getYear()-parziale.get(0).getDateEventBegan().getYear())<=this.anniMax)
			return true;
		else 
			return false;
	}

	public int getPersone(List<PowerOutages> parziale) {
		int persone=0;
		for(PowerOutages pp : parziale) {
			persone+=pp.getCustomersAffected();
		}
		return persone;
	}

	private boolean rispettaCondizioneOre(List<PowerOutages> parziale, PowerOutages pp) {
		int ore = 0;
		for(PowerOutages p : parziale) {
			ore+=p.getOreTot();
		}
		ore+=pp.getOreTot();
		if(ore<=this.oreMax)
			return true;
		else
			return false;
	}
	
	public int getOre(List<PowerOutages> parziale) {
		int ore=0;
		for(PowerOutages p : parziale) {
			ore+=p.getOreTot();
		}
		return ore;
	}

}
