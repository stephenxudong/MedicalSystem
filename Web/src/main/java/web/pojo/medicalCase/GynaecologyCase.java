package web.pojo.medicalCase;

import java.util.Date;
public class GynaecologyCase extends Case {

    private String personal_case;//个人史
    private String marital_history;//婚姻史
    private String menstruation_history;
    private String bearing_history;
    private String family_history;
    private String physical_examination;

    public GynaecologyCase(String case_id, String status,
                           String main_case, String present_history,
                           String past_history, String doctor_account_id,
                           Date date,String mtime,String is_submit,
                           String patient_account_id, String patient_name,
                           String patient_identification_id,
                           String patient_phone_number, String sex,
                           String personal_case, String marital_history,
                           String menstruation_history, String bearing_history,
                           String family_history, String physical_examination) {
        super(case_id, status, main_case,
                present_history, past_history,
                doctor_account_id,date,mtime, patient_account_id, patient_name,
                patient_identification_id, patient_phone_number,sex,is_submit);
        this.personal_case = personal_case;
        this.marital_history = marital_history;
        this.menstruation_history = menstruation_history;
        this.bearing_history = bearing_history;
        this.family_history = family_history;
        this.physical_examination = physical_examination;
    }

    public GynaecologyCase(){
        super();
    }

    public String getPersonal_case() {
        return personal_case;
    }


    public void setPersonal_case(String personal_case) {
        this.personal_case = personal_case;
    }

    public String getMarital_history() {
        return marital_history;
    }

    public void setMarital_history(String marital_history) {
        this.marital_history = marital_history;
    }

    public String getMenstruation_history() {
        return menstruation_history;
    }

    public void setMenstruation_history(String menstruation_history) {
        this.menstruation_history = menstruation_history;
    }

    public String getBearing_history() {
        return bearing_history;
    }

    public void setBearing_history(String bearing_history) {
        this.bearing_history = bearing_history;
    }

    public String getFamily_history() {
        return family_history;
    }

    public void setFamily_history(String family_history) {
        this.family_history = family_history;
    }

    public String getPhysical_examination() {
        return physical_examination;
    }

    public void setPhysical_examination(String physical_examination) {
        this.physical_examination = physical_examination;
    }


}
