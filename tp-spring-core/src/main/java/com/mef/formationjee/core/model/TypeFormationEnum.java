package com.mef.formationjee.core.model;

public enum TypeFormationEnum {

	ENLIGNE("En ligne"),
	ENCLASSE("En classe");
	
	private String description;


	private TypeFormationEnum(String description) {
		this.description = description;
	}


	/**
     * Retrieves the {@link TypeFormationEnum} associated with the passed in description.
     *
     * @param description the description associated with the TypeFormationEnum
     * @return the TypeFormationEnum
     */
    public static TypeFormationEnum getInstance(final String description) {

        for (final TypeFormationEnum typeFormation : TypeFormationEnum.values()) {
            if (typeFormation.description == description) {
                return typeFormation;
            }
        }

        throw new IllegalArgumentException("TypeFormationEnum could not be determined with description [" + description + "]");
    }
    
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
