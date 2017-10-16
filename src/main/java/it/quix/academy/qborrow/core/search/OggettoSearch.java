package it.quix.academy.qborrow.core.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.core.search.generated.OggettoAbstractSearch;

public class OggettoSearch extends OggettoAbstractSearch {

    private static Log log = LogFactory.getLog(OggettoSearch.class);

    private static final long serialVersionUID = 7849310619150714324L;

    private Boolean isInPrestito;

    /**
     * @return the isInPrestito
     */
    public Boolean getIsInPrestito() {
        return isInPrestito;
    }

    /**
     * @param isInPrestito the isInPrestito to set
     */
    public void setIsInPrestito(Boolean isInPrestito) {
        this.isInPrestito = isInPrestito;
    }

}