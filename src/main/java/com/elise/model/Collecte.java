package com.elise.model;





public class Collecte {

	
	private int id;
	
	private String name;
	
	private String typeCollecte;
	
	private String dateDemande;
	
	private String dateCollecte;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCollecte() {
		return typeCollecte;
	}

	public void setTypeCollecte(String typeCollecte) {
		this.typeCollecte = typeCollecte;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getDateCollecte() {
		return dateCollecte;
	}

	public void setDateCollecte(String dateCollecte) {
		this.dateCollecte = dateCollecte;
	}

	@Override
	public String toString() {
		return "Collecte [id=" + id + ", name=" + name + ", typeCollecte=" + typeCollecte + ", dateDemande="
				+ dateDemande + ", dateCollecte=" + dateCollecte + "]";
	}

	public Collecte(int id, String name, String typeCollecte, String dateDemande, String dateCollecte) {
		super();
		this.id = id;
		this.name = name;
		this.typeCollecte = typeCollecte;
		this.dateDemande = dateDemande;
		this.dateCollecte = dateCollecte;
	}

	public Collecte() {
	}

	
	
}
