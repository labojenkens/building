package com.elise.service;

import java.util.List;

import com.elise.model.Collecte;

public interface CollecteService {

	public boolean addCollect(Collecte p);
	public void validateCollect(int p);
	public List<Collecte> listCollect();
	public Collecte getCollecteId(int id);
	public void removeCollecte(int id);
	
}
