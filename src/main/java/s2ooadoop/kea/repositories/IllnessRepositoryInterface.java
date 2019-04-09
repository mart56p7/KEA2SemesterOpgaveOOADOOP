package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface IllnessRepositoryInterface {

	ResultSet getIllness(int ID) throws SQLException;

	ResultSet getIllnesses() throws SQLException;

	ResultSet getIllnessTreatmentIds(int ID) throws SQLException;

	int createIllness(String Name, int[] treatment_IDs) throws SQLException;

	void editIllness(int ID, String Name, int[] treatment_IDs) throws SQLException;

	void deleteIllness(int ID) throws SQLException;

}