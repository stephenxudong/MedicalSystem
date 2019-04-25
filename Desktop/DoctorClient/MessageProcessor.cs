using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace DoctorClient
{
    class MessageProcessor
    {
        private static MessageProcessor messageProcessor;
        public static MessageProcessor getInstance() {
            if(messageProcessor == null)
                messageProcessor = new MessageProcessor();
            return messageProcessor;
        }

        public String[] getInfo(String message)
        {
            String[] infos = message.Split('#');
            return infos;
        }

        //type of Message's info
        public enum InfoType
        {
            SEND_LOGIN_Enum,
            SEND_UPDATE_Enum,
            SEND_HEART_BAET_Enum,
            SEND_REFRESH_DIR_Enum,
            SEND_REFRESH_CONTENT_Enum,
            SEND_RECEIVE_STATE_Enum,

            RECIEVE_LOGIN_STATE_Enum,
            RECIEVE_CONTENT_Enum,
            RECIEVE_DIR_Enum,
            RECIEVE_NEWDIR_Enum,
            RECIEVE_UPDATE_STATE_Enum
        }

  
        public String createInfo(InfoType infotype, String[] infos)
        {
            String message = null;

            switch (infotype)
            {
                case InfoType.SEND_LOGIN_Enum:
                    message = "$LOGIN$";
                    break;
                case InfoType.SEND_UPDATE_Enum:
                    message = "$UPDATE$";
                    break;
                case InfoType.SEND_HEART_BAET_Enum:
                    message = "$HEART_BEAT$";
                    break;
                case InfoType.SEND_REFRESH_DIR_Enum:
                    message = "$GET_ALL_DIR$";
                    break;
                case InfoType.SEND_REFRESH_CONTENT_Enum:
                    message = "$GET_CONTENT$";
                    break;
                case InfoType.SEND_RECEIVE_STATE_Enum:
                    message = "$RECEIVE_DIR$";
                    break;
            }
            if (null != infos)
                foreach (String info in infos)
                {
                    message += ("#" + info);
                }
            return message;
        }


        public  void analyseMessage(String[] message)
        {
            switch (message[0])
            {
                case "$LOGIN_STATE$":
                    recieveLoginState(message);
                    break;
                case "$UPDATE_STATE$":
                    recieveUpdateState(message);
                    break;
                case "$CONTENT$":
                    recieveContent(message);
                    break;
                case "$ALL_DIR$":
                    recieveAllDirectory(message);
                    break;
                case "$NEW_DIR$":
                    recieveNewDirectory(message);
                    break;
            }
        }


        public void recieveNewDirectory(String[] message) {
            recieveAllDirectory(message);
        }

        public void recieveContent(String[] message){
            Console.WriteLine(message[1]);
            JObject contentMessage = JObject.Parse(message[1]);
            var results = contentMessage["caseContent"].Children();
            List<GynaecologyCase> gynaecologyCases = new List<GynaecologyCase>();
            foreach (var result in results)
            {
                GynaecologyCase gynaecology = GynaecologyCase.DeserializeObject(result.ToString());
                MedicalCaseManager.getInstance().addNewMedicalCase(gynaecology);
                gynaecologyCases.Add(gynaecology);
            }

            MedicalCaseForm.getInstance().updateMedicalCaseDirectory(gynaecologyCases);
        }

        public void recieveAllDirectory(String[] message) {
            JObject dirMessage = JObject.Parse(message[1]);
            var results = dirMessage["caseID"].Children();
            List<String> allMecialCaseInfo = new List<string>();
            foreach (var result in results)
                allMecialCaseInfo.Add(result.ToString());

            String [] lackingMedicalCaseID =
                {MedicalCaseManager.getInstance().checkAllMedicalCaseDirectory(allMecialCaseInfo) };
            
            Connector.getInstance().Send(createInfo(InfoType.SEND_REFRESH_CONTENT_Enum, lackingMedicalCaseID));
        }


        public void recieveUpdateState(String[] message) {
            if (message[1] == "Failed")
            {
                throw new MyException("刷新失败, 请重试");
            }
            else if (message[1] == "Success")
            {
                 
            }
        }
        
        public void UpdateMedicalCase(GynaecologyCase gynaecologyCase)
        {
            String [] medicalCase = { gynaecologyCase.ToString() };
            Connector.getInstance().Send(createInfo(InfoType.SEND_UPDATE_Enum, medicalCase));
        } 


        public  void recieveLoginState(String[] message) {
            if (message[1] == "false")
            {
                if (message[2] == "AlreadyOnline")
                {
                    LogInForm.getInstance().receiveLogIn(true);
                    return;
                }
                    throw new MyException("登陆失败, 请重试");
            }
            else if(message[1] == "true"){
                LogInForm.getInstance().receiveLogIn(true);
            }
        }

    }
}
