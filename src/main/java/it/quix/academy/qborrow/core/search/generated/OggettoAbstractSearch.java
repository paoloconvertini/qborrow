package it.quix.academy.qborrow.core.search.generated;

import it.quix.framework.core.model.AbstractSearchModel;
import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import java.util.Date;
import java.math.BigInteger;
import it.quix.academy.qborrow.core.model.Soggetto;

import it.quix.framework.core.converter.annotation.QcDateTimeType;

/**
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class OggettoAbstractSearch extends AbstractSearchModel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String titolo;

    private String descrizione;

    private String immagine;

    private String categoria;

    @QcDateTimeType()
    private Date dataUltimaModificaFrom;

    @QcDateTimeType()
    private Date dataUltimaModificaTo;

    private Soggetto proprietario;

    private String proprietario_username;

    public void clearFilter() {
        id = null;
        titolo = null;
        descrizione = null;
        immagine = null;
        categoria = null;
        dataUltimaModificaFrom = null;
        dataUltimaModificaTo = null;
        proprietario = null;
        proprietario_username = null;
    }

    public OggettoSearch cloneFilter() {
        OggettoSearch search = new OggettoSearch();

        search.setId(id);
        search.setTitolo(titolo);
        search.setDescrizione(descrizione);
        search.setImmagine(immagine);
        search.setCategoria(categoria);
        search.setDataUltimaModificaFrom(dataUltimaModificaFrom);
        search.setDataUltimaModificaTo(dataUltimaModificaTo);
        search.setProprietario(proprietario);
        search.setProprietario_username(proprietario_username);
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
        OggettoAbstractSearch other = (OggettoAbstractSearch) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        if (titolo == null) {
            if (other.getTitolo() != null) {
                return false;
            }
        } else if (!titolo.equals(other.getTitolo())) {
            return false;
        }
        if (descrizione == null) {
            if (other.getDescrizione() != null) {
                return false;
            }
        } else if (!descrizione.equals(other.getDescrizione())) {
            return false;
        }
        if (immagine == null) {
            if (other.getImmagine() != null) {
                return false;
            }
        } else if (!immagine.equals(other.getImmagine())) {
            return false;
        }
        if (categoria == null) {
            if (other.getCategoria() != null) {
                return false;
            }
        } else if (!categoria.equals(other.getCategoria())) {
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
        if (proprietario == null) {
            if (other.getProprietario() != null) {
                return false;
            }
        } else if (!proprietario.equals(other.getProprietario())) {
            return false;
        }
        if (proprietario_username == null) {
            if (other.getProprietario_username() != null) {
                return false;
            }
        } else if (!proprietario_username.equals(other.getProprietario_username())) {
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

        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
        result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
        result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((dataUltimaModificaFrom == null) ? 0 : dataUltimaModificaFrom.hashCode());
        result = prime * result + ((dataUltimaModificaTo == null) ? 0 : dataUltimaModificaTo.hashCode());

        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        sb.append("id=").append(id);
        sb.append(", ").append("titolo=").append(titolo);
        sb.append(", ").append("descrizione=").append(descrizione);
        sb.append(", ").append("immagine=").append(immagine);
        sb.append(", ").append("categoria=").append(categoria);
        sb.append(", ").append("dataUltimaModificaFrom=").append(dataUltimaModificaFrom);
        sb.append(", ").append("dataUltimaModificaTo=").append(dataUltimaModificaTo);
        sb.append(")");
        return sb.toString();
    }

    /**
     * @return the id
     * @see OggettoSearch#id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     * @see OggettoSearch#id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the titolo
     * @see OggettoSearch#titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo the titolo to set
     * @see OggettoSearch#titolo
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return the descrizione
     * @see OggettoSearch#descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     * @see OggettoSearch#descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return the immagine
     * @see OggettoSearch#immagine
     */
    public String getImmagine() {
        return immagine;
    }

    /**
     * @param immagine the immagine to set
     * @see OggettoSearch#immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * @return the categoria
     * @see OggettoSearch#categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     * @see OggettoSearch#categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the dataUltimaModificaFrom
     * @see OggettoSearch#dataUltimaModificaFrom
     */
    public Date getDataUltimaModificaFrom() {
        return dataUltimaModificaFrom;
    }

    /**
     * @param dataUltimaModificaFrom the dataUltimaModificaFrom to set
     * @see OggettoSearch#dataUltimaModificaFrom
     */
    public void setDataUltimaModificaFrom(Date dataUltimaModificaFrom) {
        this.dataUltimaModificaFrom = dataUltimaModificaFrom;
    }

    /**
     * @return the dataUltimaModificaTo
     * @see OggettoSearch#dataUltimaModificaTo
     */
    public Date getDataUltimaModificaTo() {
        return dataUltimaModificaTo;
    }

    /**
     * @param dataUltimaModificaTo the dataUltimaModificaTo to set
     * @see OggettoSearch#dataUltimaModificaTo
     */
    public void setDataUltimaModificaTo(Date dataUltimaModificaTo) {
        this.dataUltimaModificaTo = dataUltimaModificaTo;
    }

    /**
     * @return the proprietario
     * @see OggettoSearch#proprietario
     */
    public Soggetto getProprietario() {
        return proprietario;
    }

    /**
     * @param proprietario the proprietario to set
     * @see OggettoSearch#proprietario
     */
    public void setProprietario(Soggetto proprietario) {
        this.proprietario = proprietario;
    }

    public String getProprietario_username() {
        return proprietario_username;
    }

    public void setProprietario_username(String proprietario_username) {
        this.proprietario_username = proprietario_username;
    }

    public int orderByManagement(String orderField, OrderByTypeEnum orderType) {
        if (orderField.equals("titolo")) {
            if (OrderByTypeEnum.ASC.equals(orderType)) {
                order = 1;
            } else {
                order = 2;
            }
        }

        return order;
    }

}