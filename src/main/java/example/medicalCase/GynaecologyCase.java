package example.medicalCase;

public class GynaecologyCase extends Case {

    private String personal_case;
    private String marital_history;
    private String menstruation_history;
    private String bearing_history;
    private String family_history;
    private String physical_examination;

    public GynaecologyCase(String name, String main_case, String present_history,
                           String past_history, String personal_case,
                           String marital_history, String menstruation_history,
                           String bearing_history, String family_history,
                           String physical_examination) {
        super(name, main_case, present_history, past_history);
        this.personal_case = personal_case;
        this.marital_history = marital_history;
        this.menstruation_history = menstruation_history;
        this.bearing_history = bearing_history;
        this.family_history = family_history;
        this.physical_examination = physical_examination;
    }

    public GynaecologyCase(String personal_case, String marital_history, String menstruation_history, String bearing_history, String family_history, String physical_examination) {
        this.personal_case = personal_case;
        this.marital_history = marital_history;
        this.menstruation_history = menstruation_history;
        this.bearing_history = bearing_history;
        this.family_history = family_history;
        this.physical_examination = physical_examination;
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
