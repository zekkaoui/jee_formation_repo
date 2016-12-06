package com.mef.formationjee.core.service;

import java.util.List;

import com.mef.formationjee.core.model.Formation;


public interface IFormationService {

	List<Formation> getAll();
	
	void enregistrerFormation(final Formation formation);
	
	void supprimerFormation(final Long pIdFormation);
	
	Formation getFormationParId(final Long pIdFormation);
}
