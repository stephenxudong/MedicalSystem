package example.pojo;

public class medical_case {
    private String case_id;
    private String patient_account_id;
    private String doctor_account_id;
    private String file_path;
    private doctor_account doctor_account;
    private patient_account patient_account;
    public medical_case(){
        super();
    }
    public medical_case(String case_id,String patient_account_id,String doctor_account_id,String file_path){
        this.case_id=case_id;
        this.patient_account_id=patient_account_id;
        this.doctor_account_id=doctor_account_id;
        this.file_path=file_path;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public void setPatient_account_id(String patient_account_id) {
        this.patient_account_id = patient_account_id;
    }

    public void setDoctor_account_id(String doctor_account_id) {
        this.doctor_account_id = doctor_account_id;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setDoctor_account(example.pojo.doctor_account doctor_account) {
        this.doctor_account = doctor_account;
    }

    public void setPatient_account(example.pojo.patient_account patient_account) {
        this.patient_account = patient_account;
    }

    public String getCase_id() {
        return case_id;
    }

    public String getDoctor_account_id() {
        return doctor_account_id;
    }

    public String getPatient_account_id() {
        return patient_account_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public example.pojo.doctor_account getDoctor_account() {
        return doctor_account;
    }

    public example.pojo.patient_account getPatient_account() {
        return patient_account;
    }
}
