package example.medicalCase;

public  class Case {
    private String name;
    private String main_case;
    private String present_history;
    private String past_history;

    public Case(String name, String main_case, String present_history, String past_history) {
        this.name = name;
        this.main_case = main_case;
        this.present_history = present_history;
        this.past_history = past_history;
    }

    public Case() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
