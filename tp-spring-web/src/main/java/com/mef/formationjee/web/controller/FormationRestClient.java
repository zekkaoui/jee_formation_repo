package com.mef.formationjee.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mef.formationjee.core.model.Formation;
import com.mef.formationjee.core.service.IFormationService;

@RestController
@RequestMapping(value="/restClient")
public class FormationRestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/tp-spring-web";
	
	@Autowired
	IFormationService formationService;
	
	
    /* GET */
	@RequestMapping(value="/formation/list", method = RequestMethod.GET)
    public String afficherListFormations(final ModelMap pModel) {
         
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Formation[]> usersMap =  restTemplate.getForEntity(REST_SERVICE_URI+"/formation/list", Formation[].class);
         
        Formation[] formations = usersMap.getBody();
        pModel.addAttribute("formations", formations);
        return "listformations";
    }

	/* GET */
	@RequestMapping(value="/formation/{id}", method = RequestMethod.GET)
    public String getFormation(final ModelMap pModel, @PathVariable(value="id") final Long pId) {
       
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Formation> formation = restTemplate.getForEntity(REST_SERVICE_URI+"/formation/"+pId, Formation.class);
        System.out.println(formation);
        
        pModel.addAttribute("nomFormation", formation.getBody().getTheme());
        pModel.addAttribute("nomFramework", formation.getBody().getTheme());
        
        return "formation";
    }
     
    /* POST */
    @RequestMapping(value="/enregistrerFormation", method = RequestMethod.POST)
	public String creer(@Valid @ModelAttribute(value="formationForm") Formation pFormationForm, 
	        final BindingResult pBindingResult, final ModelMap pModel, RedirectAttributes redirectAttributes) {
	
	    if (!pBindingResult.hasErrors()) {
	        if(pFormationForm.getId() == null){
	            redirectAttributes.addFlashAttribute("msg", "success.nouvelleFormation");
	        }else{
	        	 redirectAttributes.addFlashAttribute("msg", "success.modifierFormation");
	        }
	    	
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Formation> response = restTemplate.postForEntity(REST_SERVICE_URI+"/saveFormation/", pFormationForm, Formation.class);
	        
	        
	        return "redirect:/restClient/formation/list";
	    }else{
	    	if(pFormationForm.getId() == null){
	    		return nouvelleFormation(pModel);
	    	}else{
	    		return modifierFormation(pModel, pFormationForm.getId().toString());
	    	}
	    }
	}
 
    /**
	 * Supprimer la formation 
	 */
	@RequestMapping(value="/supprimerFormation", method = RequestMethod.GET)
	public String supprimer(final ModelMap pModel, @RequestParam("idFormation") final String pIdFormation,
			RedirectAttributes redirectAttributes) {
	
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/deleteFormation/" + pIdFormation);
        
	    redirectAttributes.addFlashAttribute("msg", "success.suppression");
	    return "redirect:/restClient/formation/list";
	    
	}

    
	/**
	 * Affichier le formulaire d'ajout
	 */
	@RequestMapping(value="/nouvelleFormation", method = RequestMethod.GET)
    public String nouvelleFormation(final ModelMap pModel) {
        if (pModel.get("formationForm") == null) {
            pModel.addAttribute("formationForm", new Formation());
//            pModel.addAttribute("typeList",TypeFormationEnum.values());
        }
        return "formationForm";
    }
	
	/**
	 * Affichier le formulaire de modification
	 */
	@RequestMapping(value="/modifierFormation", method = RequestMethod.GET)
	public String modifierFormation(final ModelMap pModel, @RequestParam("idFormation") final String pIdFormation) {
	    
		Formation formation = formationService.getFormationParId(Long.valueOf(pIdFormation));
		pModel.addAttribute("formationForm", formation);
	    
		return "formationForm";
	}
}
