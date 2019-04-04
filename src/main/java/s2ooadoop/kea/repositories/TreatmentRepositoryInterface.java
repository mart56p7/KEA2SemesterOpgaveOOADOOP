package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;
import s2ooadoop.kea.models.MedicineInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface TreatmentRepositoryInterface {
	public ResultSet GetTreatment(int ID) throws SQLException;
	public ResultSet GetTreatments() throws SQLException;
	public ResultSet GetTreatments(int[] ids) throws SQLException;
	public int CreateTreatment(String name, String note, int[] medicines) throws SQLException;
	public void EditTreatment(int ID, String name, String note, int[] medicines) throws SQLException;
	public void DeleteTreatment(int ID) throws SQLException;
	public ResultSet getTreatmentMedicineIds(int ID) throws SQLException;
}