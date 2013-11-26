package org.telosys.starterkits.test.common;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.operation.TransactionOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/applicationContext-test-memory-db.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, HibernateDbSetupTestListener.class })
public abstract class AbstractMemoryDBTest {

	/**
	 * Données de référentiel : ce fichier est trop volumineux et n'est pas à
	 * ouvrir dans eclipse.
	 */
	private static final String DATA_REF_FILE = "data-referentiel.xml";

	/** Répertoire des données. */
	@Value("${dataset.path}")
	private String datasetPath;

	@Value("${database.url}")
	private String jdbcUrl;

	@Value("${database.username}")
	private String jdbcUsername;

	@Value("${database.password}")
	private String jdbcPassword;

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
	 * Données des référentiels.
	 */
	private IDataSet dataSetReferentiel;

	/**
	 * Indique si le fichier des données de test existe.
	 */
	private boolean existFileDataSet;

	/**
	 * Retourne le nom du fichier XML des données à insérer pour le
	 * test.&lt;br/&gt; Ce fichier est situé dans le répertoire :
	 * "src/test/resources/data".
	 * 
	 * @return Nom du fichier XML des données à insérer.
	 */
	protected abstract String getDataSetFile();

	/**
	 * Avant l'exécution du test : insérer les données de test dans la base.
	 * 
	 * @throws Exception
	 *             Erreur.
	 */
	@Before
	public void setUpDatabase() throws Exception {
		if (StringUtils.isBlank(this.getDataSetFile())) {
			throw new IllegalStateException("Le nom du fichier de test n'est pas défini.");
		}

		File fileDataSet = new File(this.datasetPath + this.getDataSetFile());
		if (!fileDataSet.exists()) {
			throw new IllegalStateException("Le fichier de test '" + this.datasetPath + this.getDataSetFile()
					+ "' n'existe pas.");
		}

		File fileDataSetReferentiel = new File(this.datasetPath + DATA_REF_FILE);
		if (!fileDataSetReferentiel.exists()) {
			throw new IllegalStateException("Le fichier de test '" + this.datasetPath + DATA_REF_FILE
					+ "' n'existe pas.");
		}

		this.existFileDataSet = true;
		synchronized (fileDataSet) {
			this.jdbcConnection = DriverManager.getConnection(this.jdbcUrl, this.jdbcUsername, this.jdbcPassword);
			this.connection = new DatabaseConnection(this.jdbcConnection);

			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			this.dataSet = builder.build(fileDataSet);
			this.dataSetReferentiel = builder.build(fileDataSetReferentiel);

			// Créer le jeu de données de test
			this.insertDataSet(this.dataSetReferentiel);
			this.insertDataSet(this.getDataSet());
		}
	}

	/**
	 * Après l'exécution du test : nettoyage de la base des données de test.
	 * 
	 * @throws Exception
	 *             Erreur.
	 */
	@After
	public void tearDownDatabase() throws Exception {
		if (this.existFileDataSet) {
			synchronized (this.jdbcConnection) {
				// Supprimer le jeu de données de test
				this.deleteDataSet(this.dataSet);
				this.deleteDataSet(this.dataSetReferentiel);

				this.jdbcConnection.close();
				this.connection.close();
			}

		}

	}

	/**
	 * Supprimer les données de test.
	 */
	public synchronized void deleteDataSet(IDataSet dataSet) throws Exception {
		new TransactionOperation(DatabaseOperation.DELETE).execute(this.getConnection(), dataSet);
	}

	/**
	 * Insérer les données de test.
	 */
	public synchronized void insertDataSet(IDataSet dataSet) throws Exception {
		new TransactionOperation(DatabaseOperation.INSERT).execute(this.getConnection(), dataSet);
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

	/**
	 * @return the datasetPath
	 */
	public String getDatasetPath() {
		return this.datasetPath;
	}

}
