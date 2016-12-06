package com.mef.formationjee.web.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mef.formationjee.core.exception.FormationGenericException;
import com.mef.formationjee.core.model.Formation;
import com.mef.formationjee.core.service.IFormationService;


@Controller
@RequestMapping("/formation")
public class FormationController {

	final static Logger logger = Logger.getLogger(FormationController.class);
	
	@Autowired
	IFormationService formationService;
	
	/**
	 * Affichier la page de formation
	 * @throws IOException 
	 */
	@RequestMapping(value="/{nomFK}", method = RequestMethod.GET)
    public String afficherFormation(final ModelMap pModel, 
    		@PathVariable(value="nomFK") final String pNomFramework) throws IOException {
        
		if("error".equals(pNomFramework)){
			throw new FormationGenericException("error.formation.nomfk.notnull");
		}else if("exception".equals(pNomFramework)){
			throw new IOException();
		}
		
		pModel.addAttribute("nomFormation", "JEE avancée");
        pModel.addAttribute("nomFramework", pNomFramework);
        return "formation";
    }
	
	/**
	 * Affichier la liste des formations.
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
    public String afficherListFormations(final ModelMap pModel) {
        
		//logs an info message
		logger.info("Afficher la list des formations");
		
		List<Formation> formations = formationService.getAll();
		pModel.addAttribute("formations", formations);
        return "listformations";
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

	/**
	 * Créer la formation dans la base de données après validation
	 */
	@RequestMapping(value="/enregistrerFormation", method = RequestMethod.POST)
	public String creer(@Valid @ModelAttribute(value="formationForm") Formation pFormationForm, 
	        final BindingResult pBindingResult, final ModelMap pModel, RedirectAttributes redirectAttributes) {
	
	    if (!pBindingResult.hasErrors()) {
	        if(pFormationForm.getId() == null){
	            redirectAttributes.addFlashAttribute("msg", "success.nouvelleFormation");
	        }else{
	        	 redirectAttributes.addFlashAttribute("msg", "success.modifierFormation");
	        }
	    	
	        formationService.enregistrerFormation(pFormationForm);
	        
	        return "redirect:/formation/list";
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
	
	    formationService.supprimerFormation(Long.valueOf(pIdFormation));
	    redirectAttributes.addFlashAttribute("msg", "success.suppression");
	    return "redirect:/formation/list";
	    
	}
    
    
	@ExceptionHandler(FormationGenericException.class)
	public ModelAndView handleCustomException(FormationGenericException ex) {
	
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getErrCode());
	
		return model;
	
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
	
		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", "error.msgTechnique");
	
		return model;
	
	}

}
