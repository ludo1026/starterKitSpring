package org.telosys.starterkits.test.common;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Tests utilisant la vrai base de données.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring/applicationContext-test-real-db.xml",
		"/META-INF/spring/applicationContext-data.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractRealDBTest {

	/** Répertoire des données. */
	@Value("${dataset.path}")
	private String datasetPath;

	@Value("${database.url}")
	private String jdbcUrl;

	@Value("${database.username}")
	private String jdbcUsername;

	@Value("${database.password}")
	private String jdbcPassword;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Connexion JDBC.
	 */
	private Connection jdbcConnection;

	/**
	 * Connexion DBUnit.
	 */
	private IDatabaseConnection connection;

	/**
	 * Données de test.
	 */
	private IDataSet dataSet;

	/**
	 * Indique si le fichier des données de test existe.
	 */
	private boolean existFileDataSet;

	/**
	 * Retourne le nom du fichier XML des données à insérer pour le test.<br/>
	 * Ce fichier est situé dans le répertoire : "src/test/resources/data".
	 * 
	 * @return Nom du fichier XML des données à insérer.
	 */
	protected abstract String getDataFilename();

	/**
	 * Retourne le nom du fichier XML des données à supprimer pour le test.<br/>
	 * Ce fichier est situé dans le répertoire : "src/test/resources/data".
	 * 
	 * @return Nom du fichier XML des données à insérer.
	 */
	protected String getDataSetFileCleanUp() {
		return null;
	}

	private void openConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		// Pour Oracle : Décommenter la ligne suivante
		// Class.forName(OracleDriver.class.getName());
		this.jdbcConnection = DriverManager.getConnection(this.jdbcUrl, this.jdbcUsername, this.jdbcPassword);
		this.connection = new DatabaseConnection(this.jdbcConnection);
		DatabaseConfig config = this.connection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
	}

	private void closeConnection() throws SQLException {
		try {
			this.connection.close();
		} finally {
			this.jdbcConnection.close();
		}
	}

	/**
	 * Méthode appelée avant d'effectuer l'initialisation de la base pour
	 * effacer les données des tests restants.
	 * 
	 * @throws Exception Erreur.
	 */
	protected void cleanUp() throws Exception {
		// méthode à surcharger si nécessaire pour effacer les données de tests
		// restantes en base
	}

	/**
	 * Avant l'exécution du test : insérer les données de test dans la base.
	 * 
	 * @throws Exception Erreur.
	 */
	@Before
	public void setUpDatabase() throws Exception {
		if (StringUtils.isBlank(this.getDataFilename())) {
			return;
		}

		this.cleanUp();

		File fileDataSet = new File(this.datasetPath + File.separator + this.getDataFilename());
		if (!fileDataSet.exists()) {
			throw new IllegalStateException("Le fichier de test '" + this.datasetPath + File.separator
					+ this.getDataFilename() + "' n'existe pas.");
		}

		this.existFileDataSet = true;

		try {
			this.openConnection();

			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			this.dataSet = builder.build(fileDataSet);

			// Supprimer le jeu de données de test
			this.deleteDataSet();

			// Créer le jeu de données de test
			this.insertDataSet();
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Après l'exécution du test : nettoyage de la base des données de test.
	 * 
	 * @throws Exception Erreur.
	 */
	@After
	public void tearDownDatabase() throws Exception {
		String dataSetFile = null;
		if (!StringUtils.isBlank(this.getDataSetFileCleanUp())) {
			dataSetFile = this.getDataSetFileCleanUp();
		} else if (!StringUtils.isBlank(this.getDataFilename())) {
			dataSetFile = this.getDataFilename();
		}

		if (dataSetFile == null) {
			return;
		}

		this.cleanUp();

		if (this.existFileDataSet) {
			try {
				this.openConnection();

				File fileDataSet = new File(this.datasetPath + dataSetFile);
				FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
				builder.setColumnSensing(true);
				this.dataSet = builder.build(fileDataSet);

				// Supprimer le jeu de données de test
				this.deleteDataSet();
			} finally {
				this.closeConnection();
			}
		}
	}

	/**
	 * Supprimer les données de test.
	 */
	public void deleteDataSet() throws Exception {
		DatabaseOperation.DELETE.execute(this.getConnection(), this.getDataSet());
	}

	/**
	 * Insérer les données de test.
	 */
	public void insertDataSet() throws Exception {
		DatabaseOperation.INSERT.execute(this.getConnection(), this.getDataSet());
	}

	/**
	 * @return the dataSet
	 */
	public IDataSet getDataSet() {
		return this.dataSet;
	}

	/**
	 * @return the connection
	 */
	public IDatabaseConnection getConnection() {
		return this.connection;
	}

}
