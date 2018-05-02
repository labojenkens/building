package com.elise.dto;

import java.util.List;

import com.elise.model.Collecte;

public interface CollecteDto {

	public boolean addCollect(Collecte p);
	public void validateCollect(int id);
	public List<Collecte> listCollect();
	public Collecte getCollecteId(int id);
	public void removeCollecte(int id);
}
