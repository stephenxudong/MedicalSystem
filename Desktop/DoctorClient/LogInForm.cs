using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace DoctorClient
{
    public partial class LogInForm : Form
    {
        private static LogInForm form;
        private MessageProcessor messageProcessor;
        private delegate void RecieveLogIn(Boolean b);
        public LogInForm()
        {
            InitializeComponent();
            form = this;
            messageProcessor = MessageProcessor.getInstance();
        }

        private void LogInForm_Load(object sender, EventArgs e)
        {
            
            Size = new Size(1096, 647);
            StartPosition = FormStartPosition.Manual;
            int xWidth = SystemInformation.PrimaryMonitorSize.Width;
            int yHeight = SystemInformation.PrimaryMonitorSize.Height;
            Location = new Point((xWidth - Size.Width)/2, (yHeight-Size.Height)/2);
            NameInput.Text = ConfigReader.GetValue("userID");
            Password.Text = ConfigReader.GetValue("userPassword");
            Password.Select();
        }

        public static LogInForm getInstance()
        {
            return form;
        }

        public void receiveLogIn(Boolean b)
        {
            if (this.InvokeRequired)
            {
                RecieveLogIn rl = new RecieveLogIn(receiveLogIn);
                this.Invoke(rl, b);
            }
            else
            {
                MedicalCaseForm medicalCaseForm = new MedicalCaseForm();
                medicalCaseForm.Owner = this;
                medicalCaseForm.Show();
                form.Visible = false;
                ConfigReader.SetValue("userID", NameInput.Text);
                if (rememberPasswordCheckBox.Checked)
                    ConfigReader.SetValue("userPassword", Password.Text);
                else
                    ConfigReader.SetValue("userPassword", "");
            }
        }

        private void logInButton_Click(object sender, EventArgs e)
        {
            String[] logInfo = { NameInput.Text, Password.Text };
            Connector.getInstance().Send(messageProcessor.createInfo(MessageProcessor.InfoType.SEND_LOGIN_Enum, logInfo));
        }
    }
}
