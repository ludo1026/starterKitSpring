import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telosys.tools.generator.context.JavaBeanClass;
import org.telosys.tools.generator.context.JavaBeanClassAttribute;
import org.telosys.tools.generator.context.JavaBeanClassForeignKey;
import org.telosys.tools.generator.context.JavaBeanClassForeignKeyColumn;
import org.telosys.tools.generator.context.JavaBeanClassLink;

public class Tools {
	
	public void test() {
		
	}
	
	/**
	 * Retourne l'attribut de la cl�.
	 * @param entity Entit�
	 * @return Attribut de la cl�
	 */
	public JavaBeanClassAttribute keyField(JavaBeanClass entity) {
		if(entity.hasCompositePrimaryKey()) {
			return null;
		} else {
			JavaBeanClassAttribute attribute = entity.getKeyAttributes().get(0);
			return attribute;
		}
	}
	
	/**
	 * Retourne le type de la cl�.
	 * @param entity Entit�
	 * @return Type de la cl�
	 */
	public String keyType(JavaBeanClass entity) {
		if(entity.hasCompositePrimaryKey()) {
			return entity.getName()+"Id";
		} else {
			JavaBeanClassAttribute attribute = entity.getKeyAttributes().get(0);
			return attribute.formatedType(0);
		}
	}
	
	/**
	 * Retourne le nom de la cl�.
	 * @param entity Entit�
	 * @return Nom de la cl�
	 */
	public String keyName(JavaBeanClass entity) {
		if(entity.hasCompositePrimaryKey()) {
			return "id";
		} else {
			JavaBeanClassAttribute attribute = entity.getKeyAttributes().get(0);
			return attribute.formatedName(0);
		}
	}

	/**
	 * Retourne le getter de la cl�.
	 * @param entity Entit�
	 * @return Getter de la cl�
	 */
	public String keyGetter(JavaBeanClass entity) {
		if(entity.hasCompositePrimaryKey()) {
			return "getId";
		} else {
			JavaBeanClassAttribute attribute = entity.getKeyAttributes().get(0);
			return attribute.getGetter();
		}
	}

	/**
	 * Retourne le setter de la cl�.
	 * @param entity Entit�
	 * @return Setter de la cl�
	 */
	public String keySetter(JavaBeanClass entity) {
		if(entity.hasCompositePrimaryKey()) {
			return "setId";
		} else {
			JavaBeanClassAttribute attribute = entity.getKeyAttributes().get(0);
			return attribute.getSetter();
		}
	}
	
	/**
	 * Indique si le champ de la cl� primaire est une valeur auto-g�n�r�e.
	 * @param entity Entit�
	 * @param field Champ de la cl� primaire
	 * @return bool�en
	 */
	public boolean isGeneratedValue( JavaBeanClass entity, JavaBeanClassAttribute field) {
		boolean isGeneratedValue = false;
		if( field.isKeyElement() ) {
			if( ! field.isUsedInLinkJoinColumn(entity.getLinks()) ) {
				if( field.isAutoIncremented() || field.isGeneratedValue() ) {
					isGeneratedValue = true;
				}
				if( field.formatedType(0).equals("Integer")
				 || field.formatedType(0).equals("Short")
				 || field.formatedType(0).equals("Long")
				 || field.formatedType(0).equals("Double")
				 || field.formatedType(0).equals("BigInteger")
				 || field.formatedType(0).equals("BigDecimal") ) 
				{
					isGeneratedValue = true;
				}
			}
		}
		return isGeneratedValue;
	}
	
	/**
	 * Indique si l'entit� a un champ dont la valeur est auto-g�n�r�e.
	 * @param entity Entit�
	 * @param fields Champs
	 * @return bool�en
	 */
	public boolean hasGeneratedValue( JavaBeanClass entity, List<JavaBeanClassAttribute> fields) {
		boolean hasGeneratedValue = false;
		for( JavaBeanClassAttribute field : fields ) {
			if( isGeneratedValue( entity, field ) ) {
				hasGeneratedValue = true;
			}
		}
		return hasGeneratedValue;
	}

	/**
	 * Indique si le champ est utilis� par un des liens.
	 * @param attribute Champ
	 * @param links Liens
	 * @return bool�en
	 */
	public boolean isFieldUsedInLinks(JavaBeanClassAttribute attribute, List<JavaBeanClassLink> links) {
		boolean isFieldUsedInLinks = false;
		for( JavaBeanClassLink link : links ) {
			if( link.hasJoinColumns() ) {
				for( String joinColumn : link.getJoinColumns() ) {
					if( joinColumn.equals(attribute.getDatabaseName()) ) {
						isFieldUsedInLinks = true;
					}
				}
			}
		}
		return isFieldUsedInLinks;
	}

	/**
	 * Indique si le lien utilise un des champs.
	 * @param link Lien
	 * @param fields Champs
	 * @return bool�en
	 */
	public boolean isLinkUsedByFields(JavaBeanClassLink link, List<JavaBeanClassAttribute> fields) {
		boolean isLinkUsedByFields = false;
		for( JavaBeanClassAttribute field : fields ) {
			if( link.hasJoinColumns() ) {
				for( String joinColumn : link.getJoinColumns() ) {
					if( joinColumn.equals(field.getDatabaseName()) ) {
						isLinkUsedByFields = true;
					}
				}
			}
		}
		return isLinkUsedByFields;
	}
	
	/**
	 * Retourne les liens utilisant le champ.
	 * @param links Liens
	 * @param field Champ
	 * @return liens
	 */
	public List<JavaBeanClassLink> linksForField( List<JavaBeanClassLink> links, JavaBeanClassAttribute field) {
		List<JavaBeanClassLink> linksForField = new ArrayList<JavaBeanClassLink>();
		for( JavaBeanClassLink link : links ) {
			if( link.hasJoinColumns() ) {
				for( String joinColumn : link.getJoinColumns() ) {
					if( joinColumn.equals(field.getDatabaseName()) ) {
						linksForField.add(link);
					}
				}
			}
		}
		return linksForField;
	}

	/**
	 * Retourne les liens utilis�s par les champs.
	 * @param links Liens
	 * @param fields Champs
	 * @return liens
	 */
	public List<JavaBeanClassLink> linksForFields( List<JavaBeanClassLink> links, List<JavaBeanClassAttribute> fields) {
		List<JavaBeanClassLink> linksForFields = new ArrayList<JavaBeanClassLink>();
		for( JavaBeanClassAttribute field : fields ) {
			for( JavaBeanClassLink link : links ) {
				if( link.hasJoinColumns() ) {
					for( String joinColumn : link.getJoinColumns() ) {
						if( joinColumn.equals(field.getDatabaseName()) ) {
							linksForFields.add(link);
						}
					}
				}
			}
		}
		return linksForFields;
	}

	/**
	 * Champs correspondants aux liens.
	 * @param link Lien
	 * @param fields Champs
	 * @return champs
	 */
	public List<JavaBeanClassAttribute> fieldsForLink( JavaBeanClassLink link, List<JavaBeanClassAttribute> fields) {
		List<JavaBeanClassAttribute> fieldsForLink = new ArrayList<JavaBeanClassAttribute>();
		for( JavaBeanClassAttribute field : fields ) {
			if( link.hasJoinColumns() ) {
				for( String joinColumn : link.getJoinColumns() ) {
					if( joinColumn.equals(field.getDatabaseName()) ) {
						fieldsForLink.add(field);
					}
				}
			}
		}
		return fieldsForLink;
	}
	
	/**
	 * Champs correspondants aux liens
	 * @param links Liens
	 * @param fields Champs
	 * @return champs
	 */
	public List<JavaBeanClassAttribute> fieldsForLinks(List<JavaBeanClassLink> links, List<JavaBeanClassAttribute> fields) {
		List<JavaBeanClassAttribute> fieldsForLinks = new ArrayList<JavaBeanClassAttribute>();
		for( JavaBeanClassLink link : links ) {
			for( JavaBeanClassAttribute field : fields ) {
				if( link.hasJoinColumns() ) {
					for( String joinColumn : link.getJoinColumns() ) {
						if( joinColumn.equals(field.getDatabaseName()) ) {
							fieldsForLinks.add(field);
						}
					}
				}
			}
		}
		return fieldsForLinks;
	}
	
	/**
	 * Retourne la map qui a en cl� l'attribut de l'entit� de d�part et en valeur l'attribut de l'entit� cible.
	 * @param entityCurrent entit� actuelle
	 * @param entityTarget entit� cible
	 * @param link lien entre l'entit� actuelle et l'entit� cible
	 * @param fieldsOfEntityCurrent champs de l'entit� actuelle � analyser
	 * @return map des attributs
	 */
	public Map<JavaBeanClassAttribute, JavaBeanClassAttribute> fieldsMappingForLink( JavaBeanClass entityCurrent, JavaBeanClass entityTarget, JavaBeanClassLink link, List<JavaBeanClassAttribute> fieldsOfEntityCurrent) {
		Map<JavaBeanClassAttribute, JavaBeanClassAttribute> fieldsMappingForLink = new HashMap<JavaBeanClassAttribute, JavaBeanClassAttribute>(); 
		if( fieldsOfEntityCurrent != null ) {
			// Link fields
			List<JavaBeanClassAttribute> fieldsForLink = fieldsForLink( link, fieldsOfEntityCurrent );
			for( JavaBeanClassAttribute fieldForLink : fieldsForLink ) {
				// Foreign keys of current entity
				for( JavaBeanClassForeignKey fk : entityCurrent.getDatabaseForeignKeys() ) {
					// Foreign key column
					for( JavaBeanClassForeignKeyColumn fkcol : fk.getColumns() ) {
						// Search foreign key column for link field
						if( fieldForLink.getDatabaseName().equals(fkcol.getColumnName()) ) {
							// Target entity field
							for( JavaBeanClassAttribute fieldOfEntityTarget : entityTarget.getAttributes() ) {
								// Search target entity field for foreign key
								if( fieldOfEntityTarget.getDatabaseName().equals(fkcol.getReferencedColumnName()) ) {
									fieldsMappingForLink.put(fieldForLink, fieldOfEntityTarget);
								}
							}
						}
					}
				}
			}
		}
		return fieldsMappingForLink;
	}

}
