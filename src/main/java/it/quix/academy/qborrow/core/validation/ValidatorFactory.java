package it.quix.academy.qborrow.core.validation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import it.quix.framework.core.utils.ReflectionUtils;
import it.quix.framework.core.validation.api.Validator;

/**
 * Validator Factory class for every model and search model.<br>
 * Uses injection to retrieve all Validator implementation.
 *
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class ValidatorFactory implements it.quix.framework.core.validation.api.ValidatorFactory {

    /**
     * Validator for Oggetto model.
     * 
     * @see OggettoValidator
     */
    @Resource(name = "oggettoValidator")
    private OggettoValidator oggettoValidator;

    /**
     * Validator for Oggetto search model.
     * 
     * @see OggettoSearchValidator
     */
    @Resource(name = "oggettoSearchValidator")
    private OggettoSearchValidator oggettoSearchValidator;

    /**
     * Validator for Prestito model.
     * 
     * @see PrestitoValidator
     */
    @Resource(name = "prestitoValidator")
    private PrestitoValidator prestitoValidator;

    /**
     * Validator for Prestito search model.
     * 
     * @see PrestitoSearchValidator
     */
    @Resource(name = "prestitoSearchValidator")
    private PrestitoSearchValidator prestitoSearchValidator;

    /**
     * Validator for Soggetto model.
     * 
     * @see SoggettoValidator
     */
    @Resource(name = "soggettoValidator")
    private SoggettoValidator soggettoValidator;

    /**
     * Validator for Soggetto search model.
     * 
     * @see SoggettoSearchValidator
     */
    @Resource(name = "soggettoSearchValidator")
    private SoggettoSearchValidator soggettoSearchValidator;

    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Instantiate a Validator.<br>
     * Call this method to retrieve a right type Validator.
     * 
     * @param objectToValidateClass the Class of the object to be validated
     * @return Returns a new Validator instance for the objectToValidateClass type
     */
    @SuppressWarnings("unchecked")
    public Validator getInstance(Class objectToValidateClass) {
        Validator validator = null;
        // iterate over all property searching for right type
        Field[] fileds = ReflectionUtils.getFields(this.getClass());
        for (Field field : fileds) {
            if (((ParameterizedType) field.getType().getGenericSuperclass()).getActualTypeArguments()[0].equals(objectToValidateClass)) {
                validator = (Validator) ReflectionUtils.getPropertyValue(this, field.getName());
                break;
            }
        }
        return validator;
    }

    /**
     * oggettoValidator getter.
     * 
     * @return the Oggetto Validator implementation
     * @see OggettoValidator
     */
    public OggettoValidator getOggettoValidator() {
        return oggettoValidator;
    }

    /**
     * oggettoValidator setter.
     * 
     * @param oggettoValidator the Oggetto Validator implementation
     * @see OggettoValidator
     */
    public void setOggettoValidator(OggettoValidator oggettoValidator) {
        this.oggettoValidator = oggettoValidator;
    }

    /**
     * oggettoSearchValidator getter.
     * 
     * @return the OggettoSearch Validator implementation
     * @see OggettoSearchValidator
     */
    public OggettoSearchValidator getOggettoSearchValidator() {
        return oggettoSearchValidator;
    }

    /**
     * oggettoSearchValidator setter.
     * 
     * @param oggettoSearchValidator the OggettoSearch Validator implementation
     * @see OggettoSearchValidator
     */
    public void setOggettoSearchValidator(OggettoSearchValidator oggettoSearchValidator) {
        this.oggettoSearchValidator = oggettoSearchValidator;
    }

    /**
     * prestitoValidator getter.
     * 
     * @return the Prestito Validator implementation
     * @see PrestitoValidator
     */
    public PrestitoValidator getPrestitoValidator() {
        return prestitoValidator;
    }

    /**
     * prestitoValidator setter.
     * 
     * @param prestitoValidator the Prestito Validator implementation
     * @see PrestitoValidator
     */
    public void setPrestitoValidator(PrestitoValidator prestitoValidator) {
        this.prestitoValidator = prestitoValidator;
    }

    /**
     * prestitoSearchValidator getter.
     * 
     * @return the PrestitoSearch Validator implementation
     * @see PrestitoSearchValidator
     */
    public PrestitoSearchValidator getPrestitoSearchValidator() {
        return prestitoSearchValidator;
    }

    /**
     * prestitoSearchValidator setter.
     * 
     * @param prestitoSearchValidator the PrestitoSearch Validator implementation
     * @see PrestitoSearchValidator
     */
    public void setPrestitoSearchValidator(PrestitoSearchValidator prestitoSearchValidator) {
        this.prestitoSearchValidator = prestitoSearchValidator;
    }

    /**
     * soggettoValidator getter.
     * 
     * @return the Soggetto Validator implementation
     * @see SoggettoValidator
     */
    public SoggettoValidator getSoggettoValidator() {
        return soggettoValidator;
    }

    /**
     * soggettoValidator setter.
     * 
     * @param soggettoValidator the Soggetto Validator implementation
     * @see SoggettoValidator
     */
    public void setSoggettoValidator(SoggettoValidator soggettoValidator) {
        this.soggettoValidator = soggettoValidator;
    }

    /**
     * soggettoSearchValidator getter.
     * 
     * @return the SoggettoSearch Validator implementation
     * @see SoggettoSearchValidator
     */
    public SoggettoSearchValidator getSoggettoSearchValidator() {
        return soggettoSearchValidator;
    }

    /**
     * soggettoSearchValidator setter.
     * 
     * @param soggettoSearchValidator the SoggettoSearch Validator implementation
     * @see SoggettoSearchValidator
     */
    public void setSoggettoSearchValidator(SoggettoSearchValidator soggettoSearchValidator) {
        this.soggettoSearchValidator = soggettoSearchValidator;
    }

}
