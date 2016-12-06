package com.mef.formationjee.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mef.formationjee.core.dao.IFormationDAO;
import com.mef.formationjee.core.model.Formation;

@Service
public class FormationServiceImpl implements IFormationService {

	@Autowired
	IFormationDAO formationDAO;
	
	@Transactional(readOnly = true)
	public List<Formation> getAll() {
		
		return formationDAO.getAll();
	}

	@Transactional
	public void enregistrerFormation(Formation formation) {
		formationDAO.enregistrerFormation(formation);
	}

	@Transactional
	public void supprimerFormation(Long pIdFormation) {
		formationDAO.supprimerFormation(pIdFormation);
		
	}

	public Formation getFormationParId(Long pIdFormation) {
		return formationDAO.getFormationParId(pIdFormation);
	}

}
