package org.telosys.starterkits.test.data;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;

public class DBExportFromTableToXML {

	// Database connection
	static String jdbcUrl = "jdbc:oracle:thin:@<adresse IP>:<port>:<instance de base>";
	static String username = "<utilisateur>";
	static String password = "<mot de passe>";

	public static void main(String[] args) throws Exception {
		// database connection
		// For Oracle database :
		// Class.forName(OracleDriver.class.getName());
		Connection jdbcConnection = DriverManager.getConnection(jdbcUrl, username, password);
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		QueryDataSet queryDataSet = new QueryDataSet(connection);

		// Requêtes de sélection

		String nomTable = "<Nom de la table>";

		queryDataSet.addTable(nomTable, "select * from " + nomTable + " where rownum < 10");

		// Export
		String fileNameExport = "data";
		String dirExport = "target/generated";
		(new File(dirExport)).mkdirs();

		// DTD
		FlatDtdDataSet.write(queryDataSet, new FileOutputStream(dirExport + "/" + fileNameExport + ".dtd"));

		// XML
		FlatXmlWriter datasetWriter = new FlatXmlWriter(new FileOutputStream(dirExport + "/" + fileNameExport + ".xml"));
		datasetWriter.setDocType(fileNameExport + ".dtd");
		datasetWriter.write(queryDataSet);
		System.out.println("Finish !");
	}

}
