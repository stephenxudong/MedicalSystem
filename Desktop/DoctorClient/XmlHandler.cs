using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Xml;

namespace DoctorClient
{
    class XmlHandler
    {
        private static XmlHandler xmlHandler;
        private XmlDocument document;
        private XmlHandler(string filepath) {
            document = new XmlDocument();
            if (File.Exists(filepath)) {
                document.Load(filepath);
            }else{
                createXmlFile(filepath);
            }
        }

        public static XmlHandler getInstance() {
            if(xmlHandler == null){
                xmlHandler = new XmlHandler("CONFIG.xml");   
            }
            return xmlHandler;
        }

        public void createXmlFile(String filepath) {
            XmlDeclaration decl = document.CreateXmlDeclaration("1.0", "utf-8", null);
            document.AppendChild(decl);
            XmlElement rootEle = document.CreateElement("Docotors");
            document.AppendChild(rootEle);
            document.Save(filepath);
        }

        public void addDoctor(String docotorID) {
            XmlElement childEle = document.CreateElement("docotor");
            childEle.SetAttribute("id", docotorID);
            XmlNode rootEle = document.SelectSingleNode("/Docotors");
            rootEle.AppendChild(childEle);
            document.Save(document.BaseURI.Substring(8));
        }

        public void addMedicalCase(String medicalCaseID, String mtime, String docotorID) {
            XmlNode docotorNode = document.SelectSingleNode("/Docotors/docotor[@id=" + docotorID + "]");
            if(docotorNode == null){
                addDoctor(docotorID);
                docotorNode = document.SelectSingleNode("/Docotors/docotor[@id=" + docotorID + "]");
            }

            XmlNode medicalCaseNode = docotorNode.SelectSingleNode("case[@id=" + medicalCaseID + "]");
            if (medicalCaseNode == null)
            {
                XmlElement newMedicalCaseNode = document.CreateElement("case");
                newMedicalCaseNode.SetAttribute("id", medicalCaseID);
                docotorNode.AppendChild(newMedicalCaseNode);
                medicalCaseNode = newMedicalCaseNode;
            }

            medicalCaseNode.InnerText = mtime;
            document.Save(document.BaseURI.Substring(8));
        }

        public Dictionary<String, String> getAllMedicalCase(String docotorID) {
            XmlNode docotorNode = document.SelectSingleNode("/Docotors/docotor[@id=" + docotorID + "]");
            if (docotorNode == null)
                return null;
            Dictionary<String, String> allMedicalCaseInfo = new Dictionary<string, string>();
            foreach(XmlNode medicalCaseNode in docotorNode.ChildNodes)
            {            
                String a = medicalCaseNode.Attributes["id"].Value;
                String b = medicalCaseNode.InnerText;
                allMedicalCaseInfo.Add(medicalCaseNode.Attributes["id"].Value, medicalCaseNode.InnerText);
            }
            return allMedicalCaseInfo;
        }

    }                                                                                                                          
}
