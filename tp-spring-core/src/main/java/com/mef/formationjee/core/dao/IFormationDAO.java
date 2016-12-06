package com.mef.formationjee.core.dao;

import java.util.List;

import com.mef.formationjee.core.model.Formation;


public interface IFormationDAO {

	List<Formation> getAll();
	
	void enregistrerFormation(Formation formation);
	
	void supprimerFormation(final Long pIdFormation);
	
	Formation getFormationParId(final Long pIdFormation);
}
