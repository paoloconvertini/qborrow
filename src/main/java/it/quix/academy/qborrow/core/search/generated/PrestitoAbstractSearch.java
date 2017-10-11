package it.quix.academy.qborrow.core.search.generated;

import it.quix.framework.core.model.AbstractSearchModel;
import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.academy.qborrow.core.model.Oggetto;
import java.util.Date;
import java.math.BigInteger;
import it.quix.academy.qborrow.core.search.PrestitoSearch;
import it.quix.academy.qborrow.core.model.Soggetto;

import it.quix.framework.core.converter.annotation.QcDateType;

/**
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class PrestitoAbstractSearch extends AbstractSearchModel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Soggetto soggettoBeneficiario;

    private String soggettoBeneficiario_username;

    private Oggetto oggettoPrestato;

    private Integer oggettoPrestato_id;

    @QcDateType()
    private Date dataPrestitoFrom;

    @QcDateType()
    private Date dataPrestitoTo;

    @QcDateType()
    private Date dataScadenzaFrom;

    @QcDateType()
    private Date dataScadenzaTo;

    public void clearFilter() {
        soggettoBeneficiario = null;
        soggettoBeneficiario_username = null;
        oggettoPrestato = null;
        oggettoPrestato_id = null;
        dataPrestitoFrom = null;
        dataPrestitoTo = null;
        dataScadenzaFrom = null;
        dataScadenzaTo = null;
    }

    public PrestitoSearch cloneFilter() {
        PrestitoSearch search = new PrestitoSearch();

        search.setSoggettoBeneficiario(soggettoBeneficiario);
        search.setSoggettoBeneficiario_username(soggettoBeneficiario_username);
        search.setOggettoPrestato(oggettoPrestato);
        search.setOggettoPrestato_id(oggettoPrestato_id);
        search.setDataPrestitoFrom(dataPrestitoFrom);
        search.setDataPrestitoTo(dataPrestitoTo);
        search.setDataScadenzaFrom(dataScadenzaFrom);
        search.setDataScadenzaTo(dataScadenzaTo);
        return search;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relation on non-null object references:
     * <ul>
     * <li>It is reflexive: for any non-null reference value x, x.equals(x) should return true.</li>
     * <li>It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.</li>
     * <li>It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should
     * return true.</li>
     * <li>It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return
     * false, provided no information used in equals comparisons on the objects is modified.</li>
     * <li>For any non-null reference value x, x.equals(null) should return false.</li>
     * </ul>
     * The equals method for class Object implements the most discriminating possible equivalence relation on objects; that is, for
     * any non-null reference values x and y, this method returns true if and only if x and y refer to the same object (x == y has the value true).
     * Note that it is generally necessary to override the hashCode method whenever this method is overridden, so as to maintain the general contract
     * for the hashCode method, which states that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return rue if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PrestitoAbstractSearch other = (PrestitoAbstractSearch) obj;
        if (soggettoBeneficiario == null) {
            if (other.getSoggettoBeneficiario() != null) {
                return false;
            }
        } else if (!soggettoBeneficiario.equals(other.getSoggettoBeneficiario())) {
            return false;
        }
        if (soggettoBeneficiario_username == null) {
            if (other.getSoggettoBeneficiario_username() != null) {
                return false;
            }
        } else if (!soggettoBeneficiario_username.equals(other.getSoggettoBeneficiario_username())) {
            return false;
        }
        if (oggettoPrestato == null) {
            if (other.getOggettoPrestato() != null) {
                return false;
            }
        } else if (!oggettoPrestato.equals(other.getOggettoPrestato())) {
            return false;
        }
        if (oggettoPrestato_id == null) {
            if (other.getOggettoPrestato_id() != null) {
                return false;
            }
        } else if (!oggettoPrestato_id.equals(other.getOggettoPrestato_id())) {
            return false;
        }
        if (dataPrestitoFrom == null) {
            if (other.getDataPrestitoFrom() != null) {
                return false;
            }
        } else if (!dataPrestitoFrom.equals(other.getDataPrestitoFrom())) {
            return false;
        }
        if (dataPrestitoTo == null) {
            if (other.getDataPrestitoTo() != null) {
                return false;
            }
        } else if (!dataPrestitoTo.equals(other.getDataPrestitoTo())) {
            return false;
        }
        if (dataScadenzaFrom == null) {
            if (other.getDataScadenzaFrom() != null) {
                return false;
            }
        } else if (!dataScadenzaFrom.equals(other.getDataScadenzaFrom())) {
            return false;
        }
        if (dataScadenzaTo == null) {
            if (other.getDataScadenzaTo() != null) {
                return false;
            }
        } else if (!dataScadenzaTo.equals(other.getDataScadenzaTo())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hashtables such as those provided by java.util.Hashtable.
     * The general contract of hashCode is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently return the
     * same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of
     * an application to another execution of the same application.</li>
     * <li>If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same
     * integer result.</li>
     * <li>It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling the hashCode method on each of the
     * two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects
     * may improve the performance of hashtables.</li>
     * </ul>
     * 
     * @return a hash code value for this object
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((soggettoBeneficiario == null) ? 0 : soggettoBeneficiario.hashCode());
        result = prime * result + ((oggettoPrestato == null) ? 0 : oggettoPrestato.hashCode());
        result = prime * result + ((dataPrestitoFrom == null) ? 0 : dataPrestitoFrom.hashCode());
        result = prime * result + ((dataPrestitoTo == null) ? 0 : dataPrestitoTo.hashCode());
        result = prime * result + ((dataScadenzaFrom == null) ? 0 : dataScadenzaFrom.hashCode());
        result = prime * result + ((dataScadenzaTo == null) ? 0 : dataScadenzaTo.hashCode());

        result = prime * result + ((soggettoBeneficiario == null) ? 0 : soggettoBeneficiario.hashCode());
        result = prime * result + ((oggettoPrestato == null) ? 0 : oggettoPrestato.hashCode());
        return result;
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation
     * that is easy for a person to read.
     * 
     * @return a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append("(");
        sb.append("soggettoBeneficiario=").append(soggettoBeneficiario);
        sb.append("oggettoPrestato=").append(oggettoPrestato);
        sb.append(", ").append("dataPrestitoFrom=").append(dataPrestitoFrom);
        sb.append(", ").append("dataPrestitoTo=").append(dataPrestitoTo);
        sb.append(", ").append("dataScadenzaFrom=").append(dataScadenzaFrom);
        sb.append(", ").append("dataScadenzaTo=").append(dataScadenzaTo);
        sb.append(")");
        return sb.toString();
    }

    /**
     * @return the soggettoBeneficiario
     * @see PrestitoSearch#soggettoBeneficiario
     */
    public Soggetto getSoggettoBeneficiario() {
        return soggettoBeneficiario;
    }

    /**
     * @param soggettoBeneficiario the soggettoBeneficiario to set
     * @see PrestitoSearch#soggettoBeneficiario
     */
    public void setSoggettoBeneficiario(Soggetto soggettoBeneficiario) {
        this.soggettoBeneficiario = soggettoBeneficiario;
    }

    /**
     * @return the soggettoBeneficiario_username
     * @see PrestitoSearch#soggettoBeneficiario_username
     */
    public String getSoggettoBeneficiario_username() {
        return soggettoBeneficiario_username;
    }

    /**
     * @param soggettoBeneficiario_username the soggettoBeneficiario_username to set
     * @see PrestitoSearch#soggettoBeneficiario_username
     */
    public void setSoggettoBeneficiario_username(String soggettoBeneficiario_username) {
        this.soggettoBeneficiario_username = soggettoBeneficiario_username;
    }

    /**
     * @return the oggettoPrestato
     * @see PrestitoSearch#oggettoPrestato
     */
    public Oggetto getOggettoPrestato() {
        return oggettoPrestato;
    }

    /**
     * @param oggettoPrestato the oggettoPrestato to set
     * @see PrestitoSearch#oggettoPrestato
     */
    public void setOggettoPrestato(Oggetto oggettoPrestato) {
        this.oggettoPrestato = oggettoPrestato;
    }

    /**
     * @return the oggettoPrestato_id
     * @see PrestitoSearch#oggettoPrestato_id
     */
    public Integer getOggettoPrestato_id() {
        return oggettoPrestato_id;
    }

    /**
     * @param oggettoPrestato_id the oggettoPrestato_id to set
     * @see PrestitoSearch#oggettoPrestato_id
     */
    public void setOggettoPrestato_id(Integer oggettoPrestato_id) {
        this.oggettoPrestato_id = oggettoPrestato_id;
    }

    /**
     * @return the dataPrestitoFrom
     * @see PrestitoSearch#dataPrestitoFrom
     */
    public Date getDataPrestitoFrom() {
        return dataPrestitoFrom;
    }

    /**
     * @param dataPrestitoFrom the dataPrestitoFrom to set
     * @see PrestitoSearch#dataPrestitoFrom
     */
    public void setDataPrestitoFrom(Date dataPrestitoFrom) {
        this.dataPrestitoFrom = dataPrestitoFrom;
    }

    /**
     * @return the dataPrestitoTo
     * @see PrestitoSearch#dataPrestitoTo
     */
    public Date getDataPrestitoTo() {
        return dataPrestitoTo;
    }

    /**
     * @param dataPrestitoTo the dataPrestitoTo to set
     * @see PrestitoSearch#dataPrestitoTo
     */
    public void setDataPrestitoTo(Date dataPrestitoTo) {
        this.dataPrestitoTo = dataPrestitoTo;
    }

    /**
     * @return the dataScadenzaFrom
     * @see PrestitoSearch#dataScadenzaFrom
     */
    public Date getDataScadenzaFrom() {
        return dataScadenzaFrom;
    }

    /**
     * @param dataScadenzaFrom the dataScadenzaFrom to set
     * @see PrestitoSearch#dataScadenzaFrom
     */
    public void setDataScadenzaFrom(Date dataScadenzaFrom) {
        this.dataScadenzaFrom = dataScadenzaFrom;
    }

    /**
     * @return the dataScadenzaTo
     * @see PrestitoSearch#dataScadenzaTo
     */
    public Date getDataScadenzaTo() {
        return dataScadenzaTo;
    }

    /**
     * @param dataScadenzaTo the dataScadenzaTo to set
     * @see PrestitoSearch#dataScadenzaTo
     */
    public void setDataScadenzaTo(Date dataScadenzaTo) {
        this.dataScadenzaTo = dataScadenzaTo;
    }

    public int orderByManagement(String orderField, OrderByTypeEnum orderType) {
        if (orderField.equals("dataPrestito")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 1;
            } else {
                order = 2;
            }
        }
        if (orderField.equals("dataScadenza")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 3;
            } else {
                order = 4;
            }
        }

        return order;
    }

}