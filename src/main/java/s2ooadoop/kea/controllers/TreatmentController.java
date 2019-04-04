package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import s2ooadoop.kea.services.TreatmentService;

@Controller
public class TreatmentController {
    @Autowired
    TreatmentService TS;


}
