package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;
import s2ooadoop.kea.models.MedicineInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface TreatmentRepositoryInterface {
	public ResultSet getTreatment(int ID) throws SQLException;
	public ResultSet getTreatments() throws SQLException;
	public ResultSet getTreatments(int[] ids) throws SQLException;
	public int createTreatment(String name, String note, int[] medicines) throws SQLException;
	public void editTreatment(int ID, String name, String note, int[] medicines) throws SQLException;
	public void deleteTreatment(int ID) throws SQLException;
}