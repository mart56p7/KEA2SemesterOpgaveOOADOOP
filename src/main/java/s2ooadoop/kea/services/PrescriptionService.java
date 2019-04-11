package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Prescription;
import s2ooadoop.kea.repositories.PrescriptionRepository;
import s2ooadoop.kea.repositories.PrescriptionRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {
	private PrescriptionRepositoryInterface PRI = new PrescriptionRepository();
	@Autowired
    private PatientService PS;
	@Autowired
    private MedicineService MS;

	public Prescription GetPrescription(int ID) throws SQLException {
        ResultSet rs = PRI.getPrescription(ID);
        Prescription prescription = null;
        if (rs.next()) {
            prescription = new Prescription(rs.getInt("ID"), PS.getPatient(ID), rs.getString("Description"), MS.GetMedicine(ID), rs.getDate("StartDate"),rs.getDate("EndDate"));
        }
        return prescription;
	}


	public List<Prescription> GetPrescriptions(int PatientID,boolean ActiveOnly) throws SQLException {
	    ResultSet rs = PRI.getPrescriptions(PatientID,ActiveOnly);
	    List<Prescription> prescriptions = new ArrayList<>();
	    while(rs.next()){
	        prescriptions.add(new Prescription(
                    rs.getInt("ID"),
                            PS.getPatient(rs.getInt("patientID")), rs.getString("Description"), MS.GetMedicine(rs.getInt("medicineID")), rs.getDate("StartDate"),
                    rs.getDate("EndDate")
                    )
            );
        }
        return prescriptions;
    }

	public int CreatePrescription(Prescription Prescription) throws SQLException {
	    return PRI.CreatePrescription(Prescription.getPatient().getID(),Prescription.getDescription(),Prescription.getMedicine().getID(),Prescription.getStartDate(),Prescription.getEndDate());
	}

	public void EditPrescription(Prescription Prescription) throws SQLException{
		PRI.EditPrescription(Prescription.getID(),Prescription.getPatient().getID(),Prescription.getDescription(),Prescription.getMedicine().getID(),Prescription.getStartDate(),Prescription.getEndDate());
	}

	public void DeletePrescription(int ID) throws SQLException{
		PRI.DeletePrescription(ID);
	}

}