package com.mef.formationjee.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mef.formationjee.core.model.Formation;


@Repository
public class FormationDAOImpl implements IFormationDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public List<Formation> getAll() {
//		List<Formation> formations = 
//				entityManager.createQuery("select f from Formation f").getResultList();
		TypedQuery<Formation> queries = 
				entityManager.createNamedQuery(Formation.FORMATION_FIND_ALL, Formation.class);
		
		List<Formation> formations = queries.getResultList();
		
		return formations;
	}

	public void enregistrerFormation(final Formation formation) {
		entityManager.merge(formation);
	}

	public void supprimerFormation(final Long pIdFormation) {
		Formation formation = entityManager.find(Formation.class, pIdFormation);
		entityManager.remove(formation);
	}

	public Formation getFormationParId(Long pIdFormation) {
		return entityManager.find(Formation.class, pIdFormation);
	}

}
