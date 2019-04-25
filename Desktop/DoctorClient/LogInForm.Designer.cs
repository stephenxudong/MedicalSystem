namespace DoctorClient
{
    partial class LogInForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LogInForm));
            this.panel1 = new System.Windows.Forms.Panel();
            this.logInButton = new CCWin.SkinControl.SkinButton();
            this.rememberPasswordCheckBox = new CCWin.SkinControl.SkinCheckBox();
            this.NameInput = new CCWin.SkinControl.SkinTextBox();
            this.Password = new CCWin.SkinControl.SkinTextBox();
            this.bindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.Transparent;
            this.panel1.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("panel1.BackgroundImage")));
            this.panel1.Controls.Add(this.logInButton);
            this.panel1.Controls.Add(this.rememberPasswordCheckBox);
            this.panel1.Controls.Add(this.NameInput);
            this.panel1.Controls.Add(this.Password);
            this.panel1.Location = new System.Drawing.Point(0, -1);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1620, 910);
            this.panel1.TabIndex = 11;
            // 
            // logInButton
            // 
            this.logInButton.BackColor = System.Drawing.Color.Transparent;
            this.logInButton.ControlState = CCWin.SkinClass.ControlState.Normal;
            this.logInButton.DownBack = ((System.Drawing.Image)(resources.GetObject("logInButton.DownBack")));
            this.logInButton.DrawType = CCWin.SkinControl.DrawStyle.Img;
            this.logInButton.Location = new System.Drawing.Point(626, 561);
            this.logInButton.Margin = new System.Windows.Forms.Padding(4);
            this.logInButton.MouseBack = ((System.Drawing.Image)(resources.GetObject("logInButton.MouseBack")));
            this.logInButton.Name = "logInButton";
            this.logInButton.NormlBack = ((System.Drawing.Image)(resources.GetObject("logInButton.NormlBack")));
            this.logInButton.Size = new System.Drawing.Size(356, 54);
            this.logInButton.TabIndex = 166;
            this.logInButton.UseVisualStyleBackColor = false;
            this.logInButton.Click += new System.EventHandler(this.logInButton_Click);
            // 
            // rememberPasswordCheckBox
            // 
            this.rememberPasswordCheckBox.AutoSize = true;
            this.rememberPasswordCheckBox.BackColor = System.Drawing.Color.Transparent;
            this.rememberPasswordCheckBox.Checked = true;
            this.rememberPasswordCheckBox.CheckState = System.Windows.Forms.CheckState.Checked;
            this.rememberPasswordCheckBox.ControlState = CCWin.SkinClass.ControlState.Normal;
            this.rememberPasswordCheckBox.DownBack = null;
            this.rememberPasswordCheckBox.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.rememberPasswordCheckBox.ForeColor = System.Drawing.SystemColors.MenuText;
            this.rememberPasswordCheckBox.Location = new System.Drawing.Point(872, 522);
            this.rememberPasswordCheckBox.Margin = new System.Windows.Forms.Padding(4);
            this.rememberPasswordCheckBox.MouseBack = null;
            this.rememberPasswordCheckBox.Name = "rememberPasswordCheckBox";
            this.rememberPasswordCheckBox.NormlBack = null;
            this.rememberPasswordCheckBox.SelectedDownBack = null;
            this.rememberPasswordCheckBox.SelectedMouseBack = null;
            this.rememberPasswordCheckBox.SelectedNormlBack = null;
            this.rememberPasswordCheckBox.Size = new System.Drawing.Size(108, 28);
            this.rememberPasswordCheckBox.TabIndex = 10;
            this.rememberPasswordCheckBox.Text = "记住密码";
            this.rememberPasswordCheckBox.UseVisualStyleBackColor = false;
            // 
            // NameInput
            // 
            this.NameInput.BackColor = System.Drawing.Color.Transparent;
            this.NameInput.DownBack = null;
            this.NameInput.Icon = null;
            this.NameInput.IconIsButton = true;
            this.NameInput.IconMouseState = CCWin.SkinClass.ControlState.Normal;
            this.NameInput.IsPasswordChat = '\0';
            this.NameInput.IsSystemPasswordChar = false;
            this.NameInput.Lines = new string[0];
            this.NameInput.Location = new System.Drawing.Point(684, 381);
            this.NameInput.Margin = new System.Windows.Forms.Padding(0);
            this.NameInput.MaxLength = 32767;
            this.NameInput.MinimumSize = new System.Drawing.Size(28, 27);
            this.NameInput.MouseBack = null;
            this.NameInput.MouseState = CCWin.SkinClass.ControlState.Normal;
            this.NameInput.Multiline = true;
            this.NameInput.Name = "NameInput";
            this.NameInput.NormlBack = null;
            this.NameInput.Padding = new System.Windows.Forms.Padding(4);
            this.NameInput.ReadOnly = false;
            this.NameInput.ScrollBars = System.Windows.Forms.ScrollBars.None;
            this.NameInput.Size = new System.Drawing.Size(300, 56);
            // 
            // 
            // 
            this.NameInput.SkinTxt.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.NameInput.SkinTxt.Dock = System.Windows.Forms.DockStyle.Fill;
            this.NameInput.SkinTxt.Font = new System.Drawing.Font("微软雅黑", 9.75F);
            this.NameInput.SkinTxt.ForeColor = System.Drawing.SystemColors.InactiveCaptionText;
            this.NameInput.SkinTxt.Location = new System.Drawing.Point(4, 4);
            this.NameInput.SkinTxt.Multiline = true;
            this.NameInput.SkinTxt.Name = "BaseText";
            this.NameInput.SkinTxt.Size = new System.Drawing.Size(292, 48);
            this.NameInput.SkinTxt.TabIndex = 0;
            this.NameInput.SkinTxt.WaterColor = System.Drawing.Color.White;
            this.NameInput.SkinTxt.WaterText = "";
            this.NameInput.TabIndex = 9;
            this.NameInput.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.NameInput.WaterColor = System.Drawing.Color.White;
            this.NameInput.WaterText = "";
            this.NameInput.WordWrap = true;
            // 
            // Password
            // 
            this.Password.BackColor = System.Drawing.Color.Transparent;
            this.Password.DownBack = null;
            this.Password.Icon = null;
            this.Password.IconIsButton = false;
            this.Password.IconMouseState = CCWin.SkinClass.ControlState.Normal;
            this.Password.IsPasswordChat = '\0';
            this.Password.IsSystemPasswordChar = true;
            this.Password.Lines = new string[0];
            this.Password.Location = new System.Drawing.Point(684, 462);
            this.Password.Margin = new System.Windows.Forms.Padding(0);
            this.Password.MaxLength = 32767;
            this.Password.MinimumSize = new System.Drawing.Size(28, 27);
            this.Password.MouseBack = null;
            this.Password.MouseState = CCWin.SkinClass.ControlState.Normal;
            this.Password.Multiline = true;
            this.Password.Name = "Password";
            this.Password.NormlBack = null;
            this.Password.Padding = new System.Windows.Forms.Padding(4);
            this.Password.ReadOnly = false;
            this.Password.ScrollBars = System.Windows.Forms.ScrollBars.None;
            this.Password.Size = new System.Drawing.Size(300, 56);
            // 
            // 
            // 
            this.Password.SkinTxt.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.Password.SkinTxt.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Password.SkinTxt.Font = new System.Drawing.Font("微软雅黑", 9.75F);
            this.Password.SkinTxt.ForeColor = System.Drawing.SystemColors.WindowText;
            this.Password.SkinTxt.Location = new System.Drawing.Point(4, 4);
            this.Password.SkinTxt.Multiline = true;
            this.Password.SkinTxt.Name = "BaseText";
            this.Password.SkinTxt.Size = new System.Drawing.Size(292, 48);
            this.Password.SkinTxt.TabIndex = 0;
            this.Password.SkinTxt.UseSystemPasswordChar = true;
            this.Password.SkinTxt.WaterColor = System.Drawing.Color.FromArgb(((int)(((byte)(127)))), ((int)(((byte)(127)))), ((int)(((byte)(127)))));
            this.Password.SkinTxt.WaterText = "";
            this.Password.TabIndex = 8;
            this.Password.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.Password.WaterColor = System.Drawing.Color.FromArgb(((int)(((byte)(127)))), ((int)(((byte)(127)))), ((int)(((byte)(127)))));
            this.Password.WaterText = "";
            this.Password.WordWrap = true;
            // 
            // LogInForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.ClientSize = new System.Drawing.Size(1620, 909);
            this.Controls.Add(this.panel1);
            this.ForeColor = System.Drawing.SystemColors.Window;
            this.MaximumSize = new System.Drawing.Size(1642, 965);
            this.Name = "LogInForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "便携诊疗系统";
            this.Load += new System.EventHandler(this.LogInForm_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private CCWin.SkinControl.SkinButton logInButton;
        private CCWin.SkinControl.SkinCheckBox rememberPasswordCheckBox;
        private CCWin.SkinControl.SkinTextBox NameInput;
        private CCWin.SkinControl.SkinTextBox Password;
        private System.Windows.Forms.BindingSource bindingSource1;
    }
}