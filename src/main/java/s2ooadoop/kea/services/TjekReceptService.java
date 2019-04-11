package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Prescription;

@Service
public class TjekReceptService {
    public void TjekRecept(Prescription prescription) throws Exception {
        if(90 < Math.random()*100)
        {
            throw new Exception("Prescription request denied.");
        }
    }
}
