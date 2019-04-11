package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Prescription;

/**
 * Tjek Receipt service. Decides if a receipt can be given to the patient or not.
 * */

@Service
public class TjekReceptService {
    public void TjekRecept(Prescription prescription) throws Exception {
        if(90 < Math.random()*100)
        {
            throw new Exception("Prescription request denied.");
        }
    }
}
