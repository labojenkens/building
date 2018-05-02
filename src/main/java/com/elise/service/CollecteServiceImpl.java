package com.elise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elise.dto.CollecteDto;
import com.elise.model.Collecte;

@Service
public class CollecteServiceImpl implements CollecteService {

	private CollecteDto collecteDto;
	
	
	public void setCollecteDto(CollecteDto collecteDto) {
		this.collecteDto = collecteDto;
	}

	@Override
	public boolean addCollect(Collecte p) {
		return this.collecteDto.addCollect(p);
		
	}

	@Override
	public void validateCollect(int id) {
		this.collecteDto.validateCollect(id);
	}

	@Override
	public List<Collecte> listCollect() {
		return this.collecteDto.listCollect();
	}

	@Override
	public Collecte getCollecteId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCollecte(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
