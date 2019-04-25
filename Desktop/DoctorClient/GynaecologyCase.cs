using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace DoctorClient
{
    [Serializable]
    public class GynaecologyCase : Case
    {
        public String personal_case { get; set; }
        public String marital_history { get; set; }
        public String menstruation_history { get; set; }
        public String bearing_history { get; set; }
        public String family_history { get; set; }
        public String physical_examination { get; set; }

        public GynaecologyCase(String case_id,  String main_case, 
                           String present_history, String past_history, 
                           String doctor_account_id, String patient_account_id, 
                           String patient_name, String patient_identification_id,
                           String patient_phone_number, String sex,
                           String personal_case, String marital_history,
                           String menstruation_history, String bearing_history,
                           String family_history, String physical_examination, String mtime, String is_submit, String date)
                          :base(case_id, main_case,
                           present_history, past_history,
                           doctor_account_id, patient_account_id, patient_name,
                           patient_identification_id, patient_phone_number, sex, mtime, is_submit, date)
        {
            this.personal_case = personal_case;
            this.marital_history = marital_history;
            this.menstruation_history = menstruation_history;
            this.bearing_history = bearing_history;
            this.family_history = family_history;
            this.physical_examination = physical_examination;
        }

        public override string ToString(){
            return JsonConvert.SerializeObject(this);
        }

        public static GynaecologyCase DeserializeObject (String str) {
            return JsonConvert.DeserializeObject<GynaecologyCase>(str);
        }

        public  int calculateAge() { 
            string Sub_str = patient_identification_id.Substring(6, 8).Insert(4, "-").Insert(7, "-"); 
            TimeSpan ts = DateTime.Now.Subtract(Convert.ToDateTime(Sub_str));
            return ts.Days / 365;
        }

        public String getDate()
        {
            DateTime dtStart = TimeZone.CurrentTimeZone.ToLocalTime(new DateTime(1970, 1, 1));
            long lTime = long.Parse(date);
            TimeSpan toNow = new TimeSpan(lTime);
            DateTime dtResult = dtStart.Add(toNow);
            return dtResult.ToShortDateString();
        }
    }
}
