package it.quix.academy.core.pseudo;

import java.util.Date;
import java.util.Set;

import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.converter.annotation.QcDateTimeType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "soggetti") 
public class Soggetto {
	
	@Id
	@QgListColumnField
	@Column(length=50, nullable=false)
	public String username;
	
	@QgListColumnField
	@QgSearchField
	@Column(length=100, nullable=false)
	public String email;
	
	@Column(name="ragione_sociale", length=255)
	@QgListColumnField
	@QgSearchField
	public String ragioneSociale;
	
	@Column(length=50)
	@QgListColumnField
	@QgSearchField
	@QgOrderBy(type=OrderByTypeEnum.ASC, position=0)
	@QgSortable
	public String nome;
	
	@Column(length=50)
	@QgListColumnField
	@QgSearchField
	@QgOrderBy(type=OrderByTypeEnum.ASC, position=1)
	@QgSortable
	public String cognome;
	
	@Column
	@Lob
	@QgListColumnField
	public String immagine;
	
	@Temporal(TemporalType.TIMESTAMP)
	@QcDateTimeType
	@QgListColumnField
	@Column(name= "data_ultima_modifica", nullable=false)
	public Date dataUltimaModifica;
	
	@OneToMany(mappedBy="proprietario")
	public Set<Oggetto> oggetti;

	@OneToMany(mappedBy="beneficiario")
	public Set<Prestito> prestiti;
	
	
}


