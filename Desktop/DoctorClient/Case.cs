using System;
using System.Collections.Generic;
using System.Text;

namespace DoctorClient
{
    [Serializable]
    public class Case
    {

        public String main_case { get; set; }
        public String present_history { get; set; }
        public String past_history { get; set; }
        public String case_id { get; set; }
        public String doctor_account_id { get; set; }
        public String patient_account_id { get; set; }
        public String patient_name { get; set; }
        public String patient_identification_id { get; set; }
        public String patient_phone_number;
        public String sex;
        public String mtime;
        public String is_submit { get; set; }
        public String date;


        
        public Case(String case_id,  String main_case,
                       String present_history, String past_history,
                       String doctor_account_id, String patient_account_id,
                       String patient_name, String patient_identification_id,
                       String patient_phone_number, String sex, String mtime, String is_submit, String date)
        {
            this.case_id = case_id;
            this.main_case = main_case;
            this.present_history = present_history;
            this.past_history = past_history;
            this.doctor_account_id = doctor_account_id;
            this.patient_account_id = patient_account_id;
            this.patient_name = patient_name;
            this.patient_identification_id = patient_identification_id;
            this.patient_phone_number = patient_phone_number;
            this.sex = sex;
            this.mtime = mtime;
            this.is_submit = is_submit;
            this.date = date;
        }
    
       
    }
}
