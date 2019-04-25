using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace DoctorClient
{
    class MedicalCaseManager
    {
        private static MedicalCaseManager medicalCaseManager;
        private static String docotorID;
        public static MedicalCaseManager getInstance() {
            if (medicalCaseManager == null) {
                docotorID = ConfigReader.GetValue("userID");
                medicalCaseManager = new MedicalCaseManager();         
            }
            return medicalCaseManager;
        }

        public GynaecologyCase getMedicalCase(String case_id) {
            return FileHandler.getInstance().readMedicalCaseById(docotorID, case_id);
            
        }

        public List<GynaecologyCase> getAllGynaecologyCase()
        {
            List<GynaecologyCase> list = new List<GynaecologyCase>();
            List<GynaecologyCase> gynaecologyCases = new List<GynaecologyCase>();
            Dictionary<String, String> ExistingFiles = XmlHandler.getInstance().getAllMedicalCase(docotorID);
            if (ExistingFiles != null) 
            foreach (String key in ExistingFiles.Keys) {
                GynaecologyCase g = FileHandler.getInstance().readMedicalCaseById(docotorID, key);
                if(g!= null) list.Add(g);
            }
            return list;
        }
        
        public String checkAllMedicalCaseDirectory(List<String> allMedicalCaseInfo) {
            Dictionary<String, String> ExistingFiles = XmlHandler.getInstance().getAllMedicalCase(docotorID);
            List<String> lackingList = new List<string>(); 
          
      
            foreach (String medicalCaseInfo in allMedicalCaseInfo)
            {
                String[] infos = medicalCaseInfo.Split('$');
                if (ExistingFiles == null||!ExistingFiles.ContainsKey(infos[0]) || !ExistingFiles[infos[0]].Equals(infos[1]))
                    lackingList.Add(medicalCaseInfo.Split('$')[0]);
            }
            return "{\"caseID\":"+JsonConvert.SerializeObject(lackingList)+"}";
        }

        public void addNewMedicalCase(GynaecologyCase gynaecologyCase)
        {
            XmlHandler.getInstance().addMedicalCase(gynaecologyCase.case_id, gynaecologyCase.mtime, docotorID);
            FileHandler.getInstance().writeMedicalCaseById(gynaecologyCase);
        }

    }
}
