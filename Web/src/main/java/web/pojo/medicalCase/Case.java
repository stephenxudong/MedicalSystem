package web.pojo.medicalCase;

import java.util.Date;

/**
 * 此表中属于病例的基本信息，所以设计为父类
 */
public  class Case {
    private String case_id;//主键
    private String status;//是否发给app
    private String main_case;//前端返回的
    //来自前端返回的问题答案
    private String present_history;
    private String past_history;
    //只需要doctor_id
    private String doctor_account_id;
    private Date date;
    private String mtime;

    //接下来的数据来自patient_account表
    private String patient_account_id;
    private String patient_name;
    private String patient_identification_id;
    private String patient_phone_number;
    private String sex;
    private String is_submit;



    public Case(String case_id, String status, String main_case,
                String present_history, String past_history,
                String doctor_account_id, Date date,
                String mtime,String patient_account_id,
                String patient_name, String patient_identification_id,
                String patient_phone_number, String sex,String is_submit) {
        this.case_id = case_id;
        this.status = status;
        this.main_case = main_case;
        this.present_history = present_history;
        this.past_history = past_history;
        this.doctor_account_id = doctor_account_id;
        this.date = date;
        this.mtime = mtime;
        this.patient_account_id = patient_account_id;
        this.patient_name = patient_name;
        this.patient_identification_id = patient_identification_id;
        this.patient_phone_number = patient_phone_number;
        this.sex = sex;
        this.is_submit = is_submit;
    }

    public Case(){}
    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }


    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_identification_id() {
        return patient_identification_id;
    }

    public void setPatient_identification_id(String patient_identification_id) {
        this.patient_identification_id = patient_identification_id;
    }

    public String getPatient_phone_number() {
        return patient_phone_number;
    }

    public void setPatient_phone_number(String patient_phone_number) {
        this.patient_phone_number = patient_phone_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDoctor_account_id() {
        return doctor_account_id;
    }

    public void setDoctor_account_id(String doctor_account_id) {
        this.doctor_account_id = doctor_account_id;
    }

    public String getPatient_account_id() {
        return patient_account_id;
    }

    public void setPatient_account_id(String patient_account_id) {
        this.patient_account_id = patient_account_id;
    }

    public String getMain_case() {
        return main_case;
    }

    public void setMain_case(String main_case) {
        this.main_case = main_case;
    }

    public String getPresent_history() {
        return present_history;
    }

    public void setPresent_history(String present_history) {
        this.present_history = present_history;
    }

    public String getPast_history() {
        return past_history;
    }

    public void setPast_history(String past_history) {
        this.past_history = past_history;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getIs_submit() {
        return is_submit;
    }

    public void setIs_submit(String is_submit) {
        this.is_submit = is_submit;
    }
}
