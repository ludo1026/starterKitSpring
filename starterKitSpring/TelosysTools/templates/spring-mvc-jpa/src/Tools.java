import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.telosys.tools.db.model.DatabaseColumn;
import org.telosys.tools.db.model.DatabaseTable;
import org.telosys.tools.db.model.DatabaseTables;
import org.telosys.tools.generator.context.JavaBeanClass;
import org.telosys.tools.generator.context.JavaBeanClassAttribute;
import org.telosys.tools.generator.context.JavaBeanClassForeignKey;
import org.telosys.tools.generator.context.JavaBeanClassForeignKeyColumn;
import org.telosys.tools.generator.context.JavaBeanClassLink;
import org.telosys.tools.generator.context.Model;

public class Tools {
	
	public String toto(DatabaseTables databaseTables, Model model, List<JavaBeanClass> entities) {
		String str = "";
		
//		for(JavaBeanClass entity : entities) {
//			str += "\n"+entity.getName();
//			for(JavaBeanClassLink link : entity.getLinks()) {
//				str += "("+link.getJavaName()+","+link.getOptional()+")";
//			}
//		}
//		if(true) {
//			return str;
//		}
		
		Entitys entitys = new Entitys();
		for(JavaBeanClass entity : entities) {
			str += entity.getName() + ",";
			entitys.get(entity.getName());
		}
		

		str += "\n";
		
		Entity entity = entitys.getNextNotDone();
		int count = 0;
		while(entity != null && count < 30) {
			str += entity.name+",";
			explore(databaseTables, model, entity.name, entitys, 0);
			entity = entitys.getNextNotDone();
			count++;
		}
		
		str += "\n";
		
		for(Entity entity2 : entitys.entitys) {
			str += entity2.name+",";
			// export(entity2.name);
		}
		
		return str;
	}
	
	public void explore(DatabaseTables databaseTables, Model model, String current, Entitys entitys, int depth) {
		
		if(depth==15) {
			return;
		}
		
		Entity entity = entitys.get(current);
		if(entity.done) {
			return;
		}
		entity.done = true;

		boolean containsEntity = false;
		for(Entity entity2 : entitys.entitys) {
			if(entity.name.equals(entity2.name)) {
				containsEntity = true;
			}
		}
		if(!containsEntity) {
			entitys.entitys.add(entity);
		}
		
		List<String> entityNameBefores = befores(databaseTables, model.getEntityByClassName(current));
		for(String entityName : entityNameBefores) {
			entitys.setBefore(current, entityName);
			explore(databaseTables, model, entityName, entitys, depth+1);
		}

		List<String> entityNameAfters = afters(databaseTables, model.getEntityByClassName(current));
		for(String entityName : entityNameAfters) {
			entitys.setAfter(current, entityName);
			explore(databaseTables, model, entityName, entitys, depth+1);
		}
		
	}

	public static class Entitys {
		public Map<String,Entity> entityByNames = new HashMap<String,Entity>();
		public Entity get(String name) {
			Entity entity = entityByNames.get(name);
			if(entity == null) {
				entity = new Entity();
				entity.name = name;
				entityByNames.put(name, entity);
			}
			return entity;
		}
		public Entity getNextNotDone() {
			Entity entityNext = null;
			for(Entity entity : entityByNames.values()) {
				if(!entity.done) {
					entityNext = entity;
				}
			}
			return entityNext;
		}
		public LinkedList<Entity> entitys = new LinkedList<Entity>();
		public void setBefore(String entityRoot, String entityBefore) {
			setBefore(get(entityRoot), get(entityBefore));
		}
		public void setAfter(String entityRoot, String entityBefore) {
			setAfter(get(entityRoot), get(entityBefore));
		}
		public void setBefore(Entity entityRoot, Entity entityBefore) {
			entityRoot.entitysAvant.add(entityBefore);
			int index = -1;
			for(Entity entity : entitys) {
				if(entity.name.equals(entityBefore.name)) {
					return;
				}
			}
			for(Entity entity : entitys) {
				index++;
				if(entity.name.equals(entityRoot.name)) {
					break;
				}
			}
			if(index == -1) {
				// liste vide : on définit le entityRoot
				entitys.add(entityBefore);
				entitys.add(entityRoot);
			} else {
				entitys.add(index, entityBefore);
			}
		}
		public void setAfter(Entity entityRoot, Entity entityAfter) {
			entityRoot.entitysAvant.add(entityAfter);
			for(Entity entity : entitys) {
				if(entity.name.equals(entityAfter.name)) {
					return;
				}
			}
			int index = -1;
			for(Entity entity : entitys) {
				index++;
				if(entity.name.equals(entityRoot.name)) {
					break;
				}
			}
			if(index == -1) {
				// liste vide : on définit le entityRoot
				entitys.add(entityRoot);
				entitys.add(entityAfter);
			} else {
				entitys.add(index+1, entityAfter);
			}
		}
	}
	
	public static class Entity {
		public String name;
		public List<Entity> entitysAvant = new ArrayList<Tools.Entity>();
		public List<Entity> entitysApres = new ArrayList<Tools.Entity>();
		public boolean done = false;
		@Override
		public String toString() {
			return name;
		}
	}
	
	private List<String> befores(DatabaseTables databaseTables, JavaBeanClass entity) {
		List<String> befores = new ArrayList<String>();
		for(JavaBeanClassLink link : entity.getLinks()) {
			if(isBefore(databaseTables, link)) {
				befores.add(link.getTargetEntityType());
			}
		}
		return befores;
	}
	
	private List<String> afters(DatabaseTables databaseTables, JavaBeanClass entity) {
		List<String> afters = new ArrayList<String>();
		for(JavaBeanClassLink link : entity.getLinks()) {
			if(!isBefore(databaseTables, link)) {
				afters.add(link.getTargetEntityType());
			}
		}
		return afters;
	}
	
	private boolean isBefore(DatabaseTables databaseTables, JavaBeanClassLink link) {
//		boolean isBefore = link.getCardinality().equals("ManyToOne");
		boolean isBefore = false;
		DatabaseTable databaseTable = databaseTables.getTableByName(link.getJoinTable());
		for(String columnName : link.getJoinColumns()) {
			DatabaseColumn databaseColumn = databaseTable.getColumnByName(columnName);
			if(databaseColumn.isNotNull()) {
				isBefore = true;
			}
		}
		return isBefore;
	}
	
	// ------------------------------------------------------------------------
	
	
	public boolean isText(JavaBeanClassAttribute attribute) {
		String[] types = {"String"};
		return Arrays.asList(types).contains(attribute.getWrapperType());
	}
	
	public boolean isNumber(JavaBeanClassAttribute attribute) {
		String[] types = {"Integer","Long","Double","Short","Byte","Float","Number","BigInteger","BigDecimal"};
		return Arrays.asList(types).contains(attribute.getWrapperType());
	}

	public boolean isDate(JavaBeanClassAttribute attribute) {
		String[] types = {"Date","Calendar"};
		return Arrays.asList(types).contains(attribute.getWrapperType());
	}
	
	/**
	 * Retourne l'attribut de la clé.
	 * @param entity Entité
	 * @return Attribut de la clé
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
	 * Retourne le type de la clé.
	 * @param entity Entité
	 * @return Type de la clé
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
	 * Retourne le nom de la clé.
	 * @param entity Entité
	 * @return Nom de la clé
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
	 * Retourne le getter de la clé.
	 * @param entity Entité
	 * @return Getter de la clé
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
	 * Retourne le setter de la clé.
	 * @param entity Entité
	 * @return Setter de la clé
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
	 * Indique si le champ de la clé primaire est une valeur auto-générée.
	 * @param entity Entité
	 * @param field Champ de la clé primaire
	 * @return booléen
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
	 * Indique si l'entité a un champ dont la valeur est auto-générée.
	 * @param entity Entité
	 * @param fields Champs
	 * @return booléen
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
	 * Indique si le champ est utilisé par un des liens.
	 * @param attribute Champ
	 * @param links Liens
	 * @return booléen
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
	 * @return booléen
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
	 * Retourne les liens utilisés par les champs.
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
	 * Retourne la map qui a en clé l'attribut de l'entité de départ et en valeur l'attribut de l'entité cible.
	 * @param entityCurrent entité actuelle
	 * @param entityTarget entité cible
	 * @param link lien entre l'entité actuelle et l'entité cible
	 * @param fieldsOfEntityCurrent champs de l'entité actuelle à analyser
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
	
	public List<JavaBeanClassLink> links(JavaBeanClass entity, int... criterias) {
		if(criterias == null || criterias.length == 0) {
			return entity.getLinks();
		}
		List<JavaBeanClassAttribute> attributes = new ArrayList<JavaBeanClassAttribute>();
		if(criterias.length == 1) {
			attributes = entity.getAttributesByCriteria(criterias[0]);
		} else if(criterias.length == 2) {
			attributes = entity.getAttributesByCriteria(criterias[0], criterias[1]);
		} else if(criterias.length == 3) {
			attributes = entity.getAttributesByCriteria(criterias[0], criterias[1], criterias[2]);
		} else if(criterias.length == 4) {
			attributes = entity.getAttributesByCriteria(criterias[0], criterias[1], criterias[2], criterias[3]);
		} 
		// Récupérer les liens à partir des attributs
		return linksForFields(entity.getLinks(), attributes);
	}
	
}
