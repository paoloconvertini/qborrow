package it.quix.academy.qborrow.core.model;

import java.io.Serializable;
import java.lang.String;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import flexjson.JSON;
import it.quix.academy.qborrow.Configuration;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.framework.core.composer.annotation.QrExcelColumn;
import it.quix.framework.core.converter.annotation.QcDateTimeType;
import it.quix.framework.core.converter.annotation.QcDateType;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.manager.UserContextHolder;
import it.quix.framework.core.model.AttributeView;
import it.quix.framework.core.model.UserContext;
import it.quix.framework.core.validation.annotation.QvPattern;

/**
 * The Soggetto entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
@Entity
@Table(name = "soggetti")
public class Soggetto extends QborrowAbstractModel implements Serializable {

    /**
     * The serialVersionUID is a universal version identifier for a Serializable class. Deserialization uses
     * this number to ensure that a loaded class corresponds exactly to a serialized object.
     * If no match is found, then an InvalidClassException is thrown.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger object
     */
    private static Log log = LogFactory.getLog(Soggetto.class);

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * This field is part of the primary key of this entity.
     */
    @Id
    @Column(length = 50, nullable = false)
    @QvPattern(regex = "[^|]*", message = "error.qvpattern.message")
    @QrExcelColumn(order = 0)
    private String username;

    /**
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     */
    @Column(length = 100, nullable = false)
    @QrExcelColumn(order = 0)
    private String email;

    /**
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = ragione_sociale
     * <li>nullable = true
     * </ul>
     */
    @Column(name = "ragione_sociale")
    @QrExcelColumn(order = 0)
    private String ragioneSociale;

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 50)
    @QrExcelColumn(order = 0)
    private String nome;

    /**
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     */
    @Column(length = 50)
    @QrExcelColumn(order = 0)
    private String cognome;

    /**
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     */
    @Column()
    @Lob
    @QrExcelColumn(order = 0)
    private String immagine;

    /**
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     */
    @Temporal(TemporalType.TIMESTAMP)
    @QcDateTimeType()
    @Column(name = "data_ultima_modifica", nullable = false)
    @QrExcelColumn(order = 0)
    private Date dataUltimaModifica;

    /**
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = data_compleanno
     * </ul>
     */
    @Temporal(TemporalType.DATE)
    @QcDateType
    @Column(name = "data_compleanno", nullable = false)
    @QrExcelColumn(order = 0)
    private Date dataCompleanno;

    /**
	 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
    private Set<Oggetto> oggetti;

    @Transient
    private boolean oggettiJdbcAlreadyChecked = false;

    /**
	 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiario")
    private Set<Prestito> prestiti;

    @Transient
    private boolean prestitiJdbcAlreadyChecked = false;

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
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Soggetto other = (Soggetto) obj;
        if (username == null) {
            if (other.getUsername() != null) {
                return false;
            }
        } else if (!username.equals(other.getUsername())) {
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

        sb.append(", ").append("username=").append(username);

        sb.append(", ").append("email=").append(email);

        sb.append(", ").append("ragioneSociale=").append(ragioneSociale);

        sb.append(", ").append("nome=").append(nome);

        sb.append(", ").append("cognome=").append(cognome);

        sb.append(", ").append("immagine=").append(immagine);

        sb.append(", ").append("dataUltimaModifica=").append(dataUltimaModifica);

        sb.append(", ").append("dataCompleanno=").append(dataCompleanno);

        sb.append(", ").append("oggetti=").append(oggetti);

        sb.append(", ").append("prestiti=").append(prestiti);

        sb.append(")");
        return sb.toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PrePersist
    public void prePersist() {
        this.username = UUID.randomUUID().toString();
    }

    /**
     * Executed before the persist operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void prePersist(Configuration configuration) {
        prePersist();
        this.username = UUID.randomUUID().toString();
    }

    /**
     * Executed before the update operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PreUpdate
    public void preUpdate() {

    }

    /**
     * Executed before the update operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void preUpdate(Configuration configuration) {
        preUpdate();
    }

    /**
     * Executed before the delete operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    @PreRemove
    public void preRemove() {

    }

    /**
     * Executed before the delete operation is executed.
     * This method can raise a RuntimeException. The current transaction, if any, must be rolled back.
     */
    public void preRemove(Configuration configuration) {
        preRemove();

    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @return the username
     * @see Soggetto.username
     */

    public String getUsername() {
        return username;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = false
     * </ul>
     * 
     * @param username
     * @see Soggetto.username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     * 
     * @return the email
     * @see Soggetto.email
     */

    public String getEmail() {
        return email;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 100
     * <li>nullable = false
     * </ul>
     * 
     * @param email
     * @see Soggetto.email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = ragione_sociale
     * <li>nullable = true
     * </ul>
     * 
     * @return the ragioneSociale
     * @see Soggetto.ragioneSociale
     */

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = ragione_sociale
     * <li>nullable = true
     * </ul>
     * 
     * @param ragioneSociale
     * @see Soggetto.ragioneSociale
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @return the nome
     * @see Soggetto.nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @param nome
     * @see Soggetto.nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @return the cognome
     * @see Soggetto.cognome
     */

    public String getCognome() {
        return cognome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 50
     * <li>nullable = true
     * </ul>
     * 
     * @param cognome
     * @see Soggetto.cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @return the immagine
     * @see Soggetto.immagine
     */

    public String getImmagine() {
        return immagine;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>nullable = true
     * </ul>
     * 
     * @param immagine
     * @see Soggetto.immagine
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     * 
     * @return the dataUltimaModifica
     * @see Soggetto.dataUltimaModifica
     */

    public Date getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    /**
     * <br>
     * Property of field:
     * <ul>
     * <li>length = 255
     * <li>columnName = data_ultima_modifica
     * <li>nullable = false
     * </ul>
     * 
     * @param dataUltimaModifica
     * @see Soggetto.dataUltimaModifica
     */
    public void setDataUltimaModifica(Date dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }

    /**
     * @return the dataCompleanno
     */
    public Date getDataCompleanno() {
        return dataCompleanno;
    }

    /**
     * @param dataCompleanno the dataCompleanno to set
     */
    public void setDataCompleanno(Date dataCompleanno) {
        this.dataCompleanno = dataCompleanno;
    }

    /**
     * <br>
     * 
     * @return the oggetti
     * @see Soggetto.oggetti
     */

    public Set<Oggetto> getOggetti() {
        if (jdbc && !oggettiJdbcAlreadyChecked && (oggetti == null || oggetti.isEmpty())) {
            List<Oggetto> list = getQborrowManager().getOggettoListBySoggetto(username);
            oggettiJdbcAlreadyChecked = true;
            Set<Oggetto> set = new HashSet<Oggetto>();
            set.addAll(list);
            oggetti = set;
        }
        return oggetti;
    }

    /**
     * <br>
     * 
     * @param oggetti
     * @see Soggetto.oggetti
     */
    public void setOggetti(Set<Oggetto> oggetti) {
        this.oggetti = oggetti;
    }

    @JSON(include = false)
    public boolean isOggettiJdbcAlreadyChecked() {
        return oggettiJdbcAlreadyChecked;
    }

    public void setOggettiJdbcAlreadyChecked(boolean oggettiJdbcAlreadyChecked) {
        this.oggettiJdbcAlreadyChecked = oggettiJdbcAlreadyChecked;
    }

    /**
     * <br>
     * 
     * @return the prestiti
     * @see Soggetto.prestiti
     */

    public Set<Prestito> getPrestiti() {
        if (jdbc && !prestitiJdbcAlreadyChecked && (prestiti == null || prestiti.isEmpty())) {
            List<Prestito> list = getQborrowManager().getPrestitoListBySoggetto(username);
            prestitiJdbcAlreadyChecked = true;
            Set<Prestito> set = new HashSet<Prestito>();
            set.addAll(list);
            prestiti = set;
        }
        return prestiti;
    }

    /**
     * <br>
     * 
     * @param prestiti
     * @see Soggetto.prestiti
     */
    public void setPrestiti(Set<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    @JSON(include = false)
    public boolean isPrestitiJdbcAlreadyChecked() {
        return prestitiJdbcAlreadyChecked;
    }

    public void setPrestitiJdbcAlreadyChecked(boolean prestitiJdbcAlreadyChecked) {
        this.prestitiJdbcAlreadyChecked = prestitiJdbcAlreadyChecked;
    }

}