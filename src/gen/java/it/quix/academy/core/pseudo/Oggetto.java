package it.quix.academy.core.pseudo;

import java.util.Date;

import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgAttribute;
import it.quix.framework.core.codegen.annotation.QgEditField;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.codegen.annotation.SearchFieldType;
import it.quix.framework.core.converter.annotation.QcDateTimeType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Table(name = "oggetti") 
public class Oggetto {
	
	@Id
	@QgListColumnField
	@Column(length=50, nullable=false)
	public Integer id;
	
	@Column(length=255)
	@QgListColumnField
	@QgOrderBy(type=OrderByTypeEnum.ASC, position=0)
	@QgSortable
	@QgSearchField
	public String titolo;
	
	@Column
	@Lob
	@QgListColumnField
	public String descrizione;
	
	@Column
	@Lob
	@QgListColumnField
	public String immagine;
	
	@Column(length=255)
	@QgListColumnField
	@QgSearchField
	@QgAttribute(type= "QBO001_categoria")
	public String categoria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@QcDateTimeType
	@QgListColumnField
	@Column(name= "data_ultima_modifica", nullable=false)
	public Date dataUltimaModifica;
	
	@ManyToOne
	@QgListColumnField
	@QgSearchField
	@QgEditField(editFieldType=SearchFieldType.COMBO_SEARCH_FIELD)
	@JoinColumn(name="proprietario", nullable=false)
	public Soggetto proprietario;
	
}


