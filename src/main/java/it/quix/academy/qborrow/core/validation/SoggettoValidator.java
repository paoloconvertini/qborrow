package it.quix.academy.qborrow.core.validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.framework.core.model.UserContext;
import it.quix.framework.core.validation.api.InvalidConstraint;
import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.SoggettoSearch;

/**
 * Validator for the Soggetto object.
 * Annotation validation is automatically performed.
 * To add custom validation insert code into customValidation method and fill an InvalidConstraint set.
 *
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettoValidator extends QborrowAbstractValidator<Soggetto> {

    private static Log log = LogFactory.getLog(SoggettoValidator.class);

    /**
     * Perform custom validation on a Soggetto object
     * 
     * @param userContext user context of the logged user
     * @param errors set of invalid constraint to fill with this method, if errors are present
     * @param propertyPath the path of the object in the objectGraph. It uses
     *            .notation like user.address[0]e
     * @param soggetto object to validate
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     */
    @Override
    protected void customValidation(UserContext userContext, Set<InvalidConstraint<?>> errors, String propertyPath, Soggetto soggetto, String... groups) {
        if (log.isDebugEnabled()) {
            log.debug("Call customValidation method for class SoggettoValidator ");
        }

        if (soggetto.getDataCompleanno() != null) {

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            
            int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

            Calendar cal = Calendar.getInstance();

            cal.setTime(soggetto.getDataCompleanno());

            int yearCompleanno = cal.get(Calendar.YEAR);

            int monthCompleanno = cal.get(Calendar.MONTH);
            
            int dayCompleanno = cal.get(Calendar.DAY_OF_MONTH);

            if (currentMonth - monthCompleanno < 0) {
                currentYear = currentYear - 1;
            } else if (currentDay - dayCompleanno < 0) {
            	currentYear = currentYear - 1;
            }

            if (!soggetto.getDataCompleanno().before(new Date())) {
                System.out.println("La data non può essere futura");
                InvalidConstraint<Soggetto> ic1 =
                    new InvalidConstraintImpl<Soggetto>(Soggetto.class, "error.dataNelFuturo", propertyPath + "dataCompleanno", soggetto,
                        soggetto.getDataCompleanno());
                errors.add(ic1);
            } else if (currentYear - yearCompleanno < 18) {
                InvalidConstraint<Soggetto> ic2 =
                    new InvalidConstraintImpl<Soggetto>(Soggetto.class, "error.NonMaggiorenne", propertyPath + "dataCompleanno", soggetto,
                        soggetto.getDataCompleanno());
                errors.add(ic2);
            }

        }
        // insert here custom validations for Soggetto model
        // after a validation check fail create a new InvalidContraint of the validated type
        // and instantiate an InvalidContraintImpl of the validated type with the error information, es:
        // InvalidConstraint<Type> ic = new InvalidConstraintImpl<Type>(Type.class, message, propertyPath, rootBean, value);
        // where:
        // [message] is the name of a bundle which contains the appropriate error message
        // [propertyPath] is the path from the root bean to the property which generated the error
        // [rootBean] is the root bean being validated
        // [value] is the value of the property which generated the error
        // in the end, add the new invalid constraint to the errors array, es:
        // errors.add(ic);
    }

}