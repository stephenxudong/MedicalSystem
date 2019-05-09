package web.dao;

import web.pojo.Patient_account;

public interface Patient_accountMapper {
    Patient_account findByPatientId(String patient_account_id);
    void updatePatientAccount(Patient_account patient_account);
    void insertPatient(Patient_account patient_account);
    Patient_account selectByIdentiId(String patient_identification_id);


}
