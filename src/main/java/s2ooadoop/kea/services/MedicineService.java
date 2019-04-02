package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.repositories.MedicineRepositoryInterface;

@Service
public class MedicineService {

	private MedicineRepositoryInterface MRI;

	/**
	 * 
	 * @param ID
	 */
	public Medicine GetMedicine(int ID) {
		// TODO - implement MedicineService.GetMedicine
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Medicine
	 */
	public int CreateMedicine(Medicine Medicine) {
		// TODO - implement MedicineService.CreateMedicine
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Medicine
	 */
	public void EditMedicine(Medicine Medicine) {
		// TODO - implement MedicineService.EditMedicine
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void DeleteMedicine(int ID) {
		// TODO - implement MedicineService.DeleteMedicine
		throw new UnsupportedOperationException();
	}

}