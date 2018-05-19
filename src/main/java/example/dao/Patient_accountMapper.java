package example.dao;

import example.pojo.Patient_account;

public interface Patient_accountMapper {
    Patient_account findByPatientId(String patient_account_id);
    void updatePatientAccount(Patient_account patient_account);
    void insertPatient(Patient_account patient_account);


}
