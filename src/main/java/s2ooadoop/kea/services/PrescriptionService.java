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
	@Autowired
	private TjekReceptService TJ;

	public Prescription GetPrescription(int ID) throws SQLException {
        ResultSet rs = PRI.getPrescription(ID);
        Prescription prescription = null;
        if (rs.next()) {
            prescription = new Prescription(
            		rs.getInt("id"),
					PS.getPatient(rs.getInt("patient_id")),
					rs.getString("description"),
					MS.GetMedicine(rs.getInt("medicine_id")),
					rs.getDate("startdate"),
					rs.getDate("enddate")
			);
        }
        return prescription;
	}


	public List<Prescription> GetPrescriptions(int PatientID,boolean ActiveOnly) throws SQLException {
	    ResultSet rs = PRI.getPrescriptions(PatientID,ActiveOnly);
	    List<Prescription> prescriptions = new ArrayList<>();
	    while(rs.next()){
	        prescriptions.add(new Prescription(
                    rs.getInt("id"),
					PS.getPatient(rs.getInt("patient_id")),
					rs.getString("description"),
					MS.GetMedicine(rs.getInt("medicine_id")),
					rs.getDate("startdate"),
                    rs.getDate("enddate")
                    )
            );
        }

        return prescriptions;
    }

	public int CreatePrescription(Prescription pre) throws SQLException, Exception {
		TJ.TjekRecept(pre);
		return PRI.CreatePrescription(pre.getPatient().getID(), pre.getDescription(), pre.getMedicine().getID(), pre.getStartDate(), pre.getEndDate());
	}

	public void EditPrescription(Prescription Prescription) throws SQLException{
		PRI.EditPrescription(Prescription.getID(),Prescription.getPatient().getID(),Prescription.getDescription(),Prescription.getMedicine().getID(),Prescription.getStartDate(),Prescription.getEndDate());
	}

	public void DeletePrescription(int ID) throws SQLException{
		PRI.DeletePrescription(ID);
	}

}