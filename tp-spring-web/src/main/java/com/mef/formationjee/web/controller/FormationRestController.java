package com.mef.formationjee.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mef.formationjee.core.model.Formation;
import com.mef.formationjee.core.service.IFormationService;


@RestController
@RequestMapping("/rest")
public class FormationRestController {

	final static Logger logger = Logger.getLogger(FormationRestController.class);
	
	@Autowired
	IFormationService formationService;
	
	/**
	 * Service REST pour rechercher une liste des formations
	 */
	@RequestMapping(value = "/formations", method = RequestMethod.GET)
	public ResponseEntity<List<Formation>> listAllFormations() {
	    List<Formation> formations = formationService.getAll();
	    if(formations.isEmpty()){
	        return new ResponseEntity<List<Formation>>(HttpStatus.NO_CONTENT);//vou pouvez retourner HttpStatus.NOT_FOUND
	    }
	    return new ResponseEntity<List<Formation>>(formations, HttpStatus.OK);
	}

	/**
	 * Service REST pour rechercher un objet par ID
	 */
	@RequestMapping(value="/formation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Formation> getFormation(@PathVariable(value="id") final Long pId) {
        
		System.out.println("Fetching Formation with id " + pId);
		Formation formation = formationService.getFormationParId(pId);
	    if (formation == null) {
	        System.out.println("Formation with id " + pId + " not found");
	        return new ResponseEntity<Formation>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<Formation>(formation, HttpStatus.OK);
    }

	/**
	 * Service REST pour enregistrer une formation
	 */
	@RequestMapping(value="/saveFormation/", method = RequestMethod.POST)
	public ResponseEntity<Formation> creer(@RequestBody Formation pFormation) {
	
		System.out.println("Creating Formation " + pFormation.getTheme());
		
		try {
			formationService.enregistrerFormation(pFormation);
		} catch (Exception e) {
			System.out.println("Exception in Creating Formation !");
			return new ResponseEntity<Formation>(HttpStatus.NOT_IMPLEMENTED);
		}
		
	    return new ResponseEntity<Formation>(HttpStatus.CREATED);
	    
	}
	
	/**
	 * Service REST pour modifier une formation
	 */
	@RequestMapping(value = "/updateFormation/", method = RequestMethod.PUT)
	public ResponseEntity<Formation> updateFormation(@RequestBody Formation formation) {
	    System.out.println("Updating Formation " + formation.getId());
	     
	    Formation currentFormation = formationService.getFormationParId(formation.getId());
	     
	    if (currentFormation==null) {
	    	System.out.println("Formation with id " + formation.getId() + " not found");
	        return new ResponseEntity<Formation>(HttpStatus.NOT_FOUND);
	    }
	 
	    formationService.enregistrerFormation(currentFormation);
	    
	    return new ResponseEntity<Formation>(currentFormation, HttpStatus.OK);
	}

	/**
	 * Sservice REST pour supprimer une formation
	 */
	@RequestMapping(value = "/deleteFormation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Formation> deleteFormation(@PathVariable("id") Long id) {
	    System.out.println("Fetching & Deleting Formation with id " + id);
	 
	    Formation formation = formationService.getFormationParId(id);
	    if (formation == null) {
	        System.out.println("Unable to delete. Formation with id " + id + " not found");
	        return new ResponseEntity<Formation>(HttpStatus.NOT_FOUND);
	    }
	 
	    formationService.supprimerFormation(id);
	        
	    return new ResponseEntity<Formation>(HttpStatus.NO_CONTENT);    
	}

	
	//Les services .....

}
