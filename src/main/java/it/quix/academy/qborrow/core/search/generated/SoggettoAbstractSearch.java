package it.quix.academy.qborrow.core.search.generated;

import it.quix.framework.core.model.AbstractSearchModel;
import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.academy.qborrow.core.model.Oggetto;
import java.util.Date;
import it.quix.academy.qborrow.core.model.Prestito;
import java.math.BigInteger;
import it.quix.academy.qborrow.core.search.SoggettoSearch;

import it.quix.framework.core.converter.annotation.QcDateTimeType;

/**
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettoAbstractSearch extends AbstractSearchModel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String email;

    private String ragioneSociale;

    private String nome;

    private String cognome;

    private String immagine;

    @QcDateTimeType()
    private Date dataUltimaModificaFrom;

    @QcDateTimeType()
    private Date dataUltimaModificaTo;

    private Oggetto oggetto; // b

    private Prestito prestito; // b

    public void clearFilter() {
        username = null;
        email = null;
        ragioneSociale = null;
        nome = null;
        cognome = null;
        immagine = null;
        dataUltimaModificaFrom = null;
        dataUltimaModificaTo = null;
    }

    public SoggettoSearch cloneFilter() {
        SoggettoSearch search = new SoggettoSearch();

        search.setUsername(username);
        search.setEmail(email);
        search.setRagioneSociale(ragioneSociale);
        search.setNome(nome);
        search.setCognome(cognome);
        search.setImmagine(immagine);
        search.setDataUltimaModificaFrom(dataUltimaModificaFrom);
        search.setDataUltimaModificaTo(dataUltimaModificaTo);
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
        SoggettoAbstractSearch other = (SoggettoAbstractSearch) obj;
        if (username == null) {
            if (other.getUsername() != null) {
                return false;
            }
        } else if (!username.equals(other.getUsername())) {
            return false;
        }
        if (email == null) {
            if (other.getEmail() != null) {
                return false;
            }
        } else if (!email.equals(other.getEmail())) {
            return false;
        }
        if (ragioneSociale == null) {
            if (other.getRagioneSociale() != null) {
                return false;
            }
        } else if (!ragioneSociale.equals(other.getRagioneSociale())) {
            return false;
        }
        if (nome == null) {
            if (other.getNome() != null) {
                return false;
            }
        } else if (!nome.equals(other.getNome())) {
            return false;
        }
        if (cognome == null) {
            if (other.getCognome() != null) {
                return false;
            }
        } else if (!cognome.equals(other.getCognome())) {
            return false;
        }
        if (immagine == null) {
            if (other.getImmagine() != null) {
                return false;
            }
        } else if (!immagine.equals(other.getImmagine())) {
            return false;
        }
        if (dataUltimaModificaFrom == null) {
            if (other.getDataUltimaModificaFrom() != null) {
                return false;
            }
        } else if (!dataUltimaModificaFrom.equals(other.getDataUltimaModificaFrom())) {
            return false;
        }
        if (dataUltimaModificaTo == null) {
            if (other.getDataUltimaModificaTo() != null) {
                return false;
            }
        } else if (!dataUltimaModificaTo.equals(other.getDataUltimaModificaTo())) {
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

        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
        result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
        result = prime * result + ((dataUltimaModificaFrom == null) ? 0 : dataUltimaModificaFrom.hashCode());
        result = prime * result + ((dataUltimaModificaTo == null) ? 0 : dataUltimaModificaTo.hashCode());

        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        sb.append("username=").append(username);
        sb.append(", ").append("email=").append(email);
        sb.append(", ").append("ragioneSociale=").append(ragioneSociale);
        sb.append(", ").append("nome=").append(nome);
        sb.append(", ").append("cognome=").append(cognome);
        sb.append(", ").append("immagine=").append(immagine);
        sb.append(", ").append("dataUltimaModificaFrom=").append(dataUltimaModificaFrom);
        sb.append(", ").append("dataUltimaModificaTo=").append(dataUltimaModificaTo);
        sb.append(")");
        return sb.toString();
    }

    /**
     * @return the username
     * @see SoggettoSearch#username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     * @see SoggettoSearch#username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     * @see SoggettoSearch#email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     * @see SoggettoSearch#email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the ragioneSociale
     * @see SoggettoSearch#ragioneSociale
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * @param ragioneSociale the ragioneSociale to set
     * @see SoggettoSearch#ragioneSociale
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * @return the nome
     * @see SoggettoSearch#nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     * @see SoggettoSearch#nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cognome
     * @see SoggettoSearch#cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     * @see SoggettoSearch#cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the immagine
     * @see SoggettoSearch#immagine
     */
    public String getImmagine() {
        return immagine;
    }

    /**
     * @param immagine the immagine to set
     * @see SoggettoSearch#immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * @return the dataUltimaModificaFrom
     * @see SoggettoSearch#dataUltimaModificaFrom
     */
    public Date getDataUltimaModificaFrom() {
        return dataUltimaModificaFrom;
    }

    /**
     * @param dataUltimaModificaFrom the dataUltimaModificaFrom to set
     * @see SoggettoSearch#dataUltimaModificaFrom
     */
    public void setDataUltimaModificaFrom(Date dataUltimaModificaFrom) {
        this.dataUltimaModificaFrom = dataUltimaModificaFrom;
    }

    /**
     * @return the dataUltimaModificaTo
     * @see SoggettoSearch#dataUltimaModificaTo
     */
    public Date getDataUltimaModificaTo() {
        return dataUltimaModificaTo;
    }

    /**
     * @param dataUltimaModificaTo the dataUltimaModificaTo to set
     * @see SoggettoSearch#dataUltimaModificaTo
     */
    public void setDataUltimaModificaTo(Date dataUltimaModificaTo) {
        this.dataUltimaModificaTo = dataUltimaModificaTo;
    }

    /**
     * @return the oggetto
     * @see SoggettoSearch#oggetto
     */
    public Oggetto getOggetto() {
        return oggetto;
    }

    /**
     * @param oggetto the oggetto to set
     * @see SoggettoSearch#oggetto
     */
    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * @return the prestito
     * @see SoggettoSearch#prestito
     */
    public Prestito getPrestito() {
        return prestito;
    }

    /**
     * @param prestito the prestito to set
     * @see SoggettoSearch#prestito
     */
    public void setPrestito(Prestito prestito) {
        this.prestito = prestito;
    }

    public int orderByManagement(String orderField, OrderByTypeEnum orderType) {
        if (orderField.equals("nome")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 1;
            } else {
                order = 2;
            }
        }
        if (orderField.equals("cognome")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 3;
            } else {
                order = 4;
            }
        }

        return order;
    }

}