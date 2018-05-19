package example.pojo;

import java.util.List;

public class Patient_account {
    private String patient_account_id;
    private String patient_name;
    private String patient_phone_number;
    private String patient_identification_id;
    private List<medical_case> medical_caselist;
    public Patient_account(){
        super();
    }

    public Patient_account(String patient_account_id, String patient_name, String patient_phone_number, String patient_identification_id){
      this.patient_account_id=patient_account_id;
      this.patient_name=patient_name;
      this.patient_phone_number=patient_phone_number;
      this.patient_identification_id=patient_identification_id;
    }

    public void setPatient_account_id(String patient_account_id) {
        this.patient_account_id = patient_account_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_phone_number(String patient_phone_number) {
        this.patient_phone_number = patient_phone_number;
    }

    public void setPatient_identification_id(String patient_identification_id) {
        this.patient_identification_id = patient_identification_id;
    }

    public void setMedical_caselist(List<medical_case> medical_caselist) {
        this.medical_caselist = medical_caselist;
    }

    public String getPatient_account_id() {
        return patient_account_id;
    }

    public String getPatient_identification_id() {
        return patient_identification_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_phone_number() {
        return patient_phone_number;
    }

    public List<medical_case> getMedical_caselist() {
        return medical_caselist;
    }
}
