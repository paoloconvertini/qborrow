package it.quix.academy.core.pseudo;

import java.util.Date;

import it.quix.framework.core.codegen.annotation.OrderByTypeEnum;
import it.quix.framework.core.codegen.annotation.QgEditField;
import it.quix.framework.core.codegen.annotation.QgListColumnField;
import it.quix.framework.core.codegen.annotation.QgOrderBy;
import it.quix.framework.core.codegen.annotation.QgSearchField;
import it.quix.framework.core.codegen.annotation.QgSortable;
import it.quix.framework.core.codegen.annotation.SearchFieldType;
import it.quix.framework.core.converter.annotation.QcDateType;









import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Table(name = "prestiti") 
public class Prestito {
	
	@Id
	@ManyToOne
	@QgListColumnField
	@QgEditField(editFieldType=SearchFieldType.COMBO_SEARCH_FIELD)
	@JoinColumn(name="beneficiario", nullable=false)
	public Soggetto soggettoBeneficiario;
	
	@Id
	@ManyToOne
	@QgListColumnField
	@QgEditField(editFieldType=SearchFieldType.POPUP_SEARCH_FIELD)
    @JoinColumn(name="oggetto_prestato", nullable=false)
	public Oggetto oggettoPrestato;
	
	@Temporal(TemporalType.DATE)
	@QcDateType
	@QgListColumnField
	@QgSearchField
	@QgOrderBy(type=OrderByTypeEnum.DESC, position=0)
	@QgSortable
	@Column(name= "data_prestito")
	public Date dataPrestito;
	
	@Temporal(TemporalType.DATE)
	@QcDateType
	@QgListColumnField
	@QgSearchField
	@QgOrderBy(type=OrderByTypeEnum.DESC, position=1)
	@QgSortable
	@Column(name= "data_scadenza")
	public Date dataScadenza;
}


