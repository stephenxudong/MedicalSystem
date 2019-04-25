using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace DoctorClient
{
    class FileHandler
    {
        private static FileHandler fileHandler;

        public static FileHandler getInstance() {
            if (fileHandler == null) {
                fileHandler = new FileHandler();
            }
            return fileHandler;
        }

        public List<String> getAllLackingFile(String docotorID, String[] allMedicalCaseID) {
            List<String> lackingFile = new List<string>();
            foreach (String medicalCaseID in allMedicalCaseID)            {
                if (!File.Exists(getMedicalCaseFilePathById(docotorID, medicalCaseID)))
                    lackingFile.Add(medicalCaseID);
            }
            return lackingFile;
        }

        public GynaecologyCase readMedicalCaseById(string docotorID, string caseID) {
            string medicalInfo;
            string filepath = getMedicalCaseFilePathById(docotorID ,caseID);
            if (File.Exists(filepath))
                medicalInfo = File.ReadAllText(filepath);
            else
                throw new MyException("该病历数据不存在，请联网重新刷新");
            return GynaecologyCase.DeserializeObject(Encryptor.AesDecrypt(medicalInfo));
        }

        public void writeMedicalCaseById( GynaecologyCase gynaecologyCase)
        {
            string medicalInfo = gynaecologyCase.ToString();
            string filepath = getMedicalCaseFilePathById(gynaecologyCase.doctor_account_id, gynaecologyCase.case_id);
            string dirpath = getMedicalCaseDirectoryPathById(gynaecologyCase.doctor_account_id, gynaecologyCase.case_id);
            if (!Directory.Exists(dirpath))
            { Directory.CreateDirectory(dirpath); }
            File.WriteAllText(filepath, Encryptor.AesEncrypt(medicalInfo));          
        }

        public string getMedicalCaseFilePathById(string docotorID, string caseID)
        {
            return "ProgramData/"+ docotorID + @"/"+ caseID + ".MEDICAL";
        }

        public string getMedicalCaseDirectoryPathById(string docotorID, string caseID)
        {
            return "ProgramData/" + docotorID;
        }
    }
}
