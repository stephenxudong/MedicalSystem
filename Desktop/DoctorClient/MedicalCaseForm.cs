using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace DoctorClient
{
    public partial class MedicalCaseForm : Form
    {

        int nCount = 0;
        int[] oldBoxHeights = { 28, 28, 28, 28, 28, 28, 28, 28 };
        RichTextBox[] richBoxes = new RichTextBox[8];
        Label[] labels = new Label[8];
        private delegate void UpdateMedicalCaseDirectory(List<GynaecologyCase> gynaecologyCases);
        private static MedicalCaseForm form;
        private static GynaecologyCase medicalCase = null;

        public MedicalCaseForm()
        {
            InitializeComponent();
            form = this;
            listView1.ListViewItemSorter = new ListViewItemComparer();
            listView2.ListViewItemSorter = new ListViewItemComparer();
            listView3.ListViewItemSorter = new ListViewItemComparer();
            listView4.ListViewItemSorter = new ListViewItemComparer();
            MedicalCaseIDLabel.Text = "";
            skinTabControl1.SelectedIndex = 0;
        }

        public static MedicalCaseForm getInstance()
        {
            return form;
        }

        public void updateMedicalCaseDirectory(List<GynaecologyCase> gynaecologyCases) {
            if (this.InvokeRequired)
            {
                UpdateMedicalCaseDirectory up = new UpdateMedicalCaseDirectory(updateMedicalCaseDirectory);
                this.Invoke(up, gynaecologyCases);
            }
            else
            {
                updateListView(gynaecologyCases);
            }
        }

        private void updateListView(List<GynaecologyCase> list)
        {
            foreach (GynaecologyCase medicalCase in list)
            {
                ListViewItem item = new ListViewItem();
                item.SubItems.Clear();
                item.SubItems[0].Text = medicalCase.case_id;
                item.SubItems.Add(medicalCase.patient_name);
                item.SubItems.Add(medicalCase.sex);
                item.Name = medicalCase.case_id;

                if (listView1.Items.ContainsKey(medicalCase.case_id))
                {
                    if (medicalCase.is_submit.Equals("true"))
                    {
                        if (listView3.Items.ContainsKey(medicalCase.case_id))
                        {
                            listView3.Items.RemoveByKey(medicalCase.case_id);
                            listView2.Items.Add(item);
                        }
                    }
                    continue;
                }

                listView1.Items.Add(item);
                if (medicalCase.is_submit.Equals("true")){
                    ListViewItem listViewItem = (ListViewItem)item.Clone();
                    listViewItem.Name = item.Name;
                    listView2.Items.Add(listViewItem);
                }
                else{
                    ListViewItem listViewItem = (ListViewItem)item.Clone();
                    listViewItem.Name = item.Name;
                    listView3.Items.Add(listViewItem);
                }
            }
            listView1.Sort(); listView2.Sort(); listView3.Sort();
        }

        private void richTextBox_ContentsResized(object sender, ContentsResizedEventArgs e)
        {
            RichTextBox textBox = (RichTextBox)sender;
            int nJudge = 100;
            int tempBoxHeight = 0;
            for (int i = 0; i < richBoxes.Length; i++)
            {
                if (textBox.Equals(richBoxes[i]))
                {
                    richBoxes[i].Height = e.NewRectangle.Height;
                    tempBoxHeight = richBoxes[i].Height;
                    nJudge = i;
                }
                if (i > nJudge)
                {
                    labels[i].Location = new Point(labels[i].Location.X, labels[i].Location.Y + tempBoxHeight - oldBoxHeights[nJudge]);
                    richBoxes[i].Location = new Point(richBoxes[i].Location.X, richBoxes[i].Location.Y + tempBoxHeight - oldBoxHeights[nJudge]);
                }
            }
            if (nCount > 16)
                oldBoxHeights[nJudge] = tempBoxHeight;
            nCount++;
        }

        private void MedicalCaseForm_Load(object sender, EventArgs e)
        {
            listView1.View = View.Details;
            listView2.View = View.Details;
            listView3.View = View.Details;
            listView4.View = View.Details;

            AutoScroll = true;
            panel3.AutoScroll = true;
            richBoxes = new RichTextBox[8] { richTextBox1, richTextBox2, richTextBox3, richTextBox4, richTextBox5, richTextBox6, richTextBox7, richTextBox8 };
            labels = new Label[8] { caseDescrip1, caseDescrip2, caseDescrip3, caseDescrip4, caseDescrip5, caseDescrip6, caseDescrip7, caseDescrip8 };
            foreach (RichTextBox tempBox in richBoxes)
                tempBox.ScrollBars = RichTextBoxScrollBars.None;
            Size = new Size(1920, 1080);

            updateListView(MedicalCaseManager.getInstance().getAllGynaecologyCase());

            Connector.getInstance().Send(
               MessageProcessor.getInstance().createInfo(MessageProcessor.InfoType.SEND_REFRESH_DIR_Enum, null));
            
        }

        private void skinButton5_Click(object sender, EventArgs e)
        {
            
        }

        private void skinButton4_Click(object sender, EventArgs e)
        {
            Connector.getInstance().Send(
               MessageProcessor.getInstance().createInfo(MessageProcessor.InfoType.SEND_REFRESH_DIR_Enum, null));
        }

        private void updateView(GynaecologyCase g) {
            DateLabel.Text = g.getDate();
            MedicalCaseIDLabel.Text = g.case_id;
            NameTextBox.Text = g.patient_name;
            SexTextBox.Text = g.sex;
            AgeTextBox.Text = g.calculateAge().ToString();
            richTextBox1.Text = g.main_case;
            richTextBox2.Text = g.present_history;
            richTextBox3.Text = g.past_history;
            richTextBox4.Text = g.marital_history;
            richTextBox5.Text = g.menstruation_history;
            richTextBox6.Text = g.bearing_history;
            richTextBox7.Text = g.family_history;
            richTextBox8.Text = g.physical_examination;
            medicalCase = g;
        }

        private GynaecologyCase getCaseFromView()
        {
            if (medicalCase != null)
            {
                medicalCase.main_case = richTextBox1.Text;
                medicalCase.present_history = richTextBox2.Text;
                medicalCase.past_history = richTextBox3.Text;
                medicalCase.marital_history = richTextBox4.Text;
                medicalCase.menstruation_history = richTextBox5.Text;
                medicalCase.bearing_history = richTextBox6.Text;
                medicalCase.family_history = richTextBox7.Text;
                medicalCase.physical_examination = richTextBox8.Text;
            }
            return medicalCase;
        }

        private void SelectIndex(object sender, EventArgs e)
        {
            ListView listView = (ListView)sender;
            if (listView.SelectedItems.Count == 0) return;
            else
            {
                string caseID = listView.SelectedItems[0].Name;
                GynaecologyCase g = MedicalCaseManager.getInstance().getMedicalCase(caseID);
                updateView(g);
            }
        }

        private void updateButton_Click(object sender, EventArgs e)
        {

        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            if(medicalCase != null)
            {
                String currentTime = System.DateTime.Now.ToString();
                medicalCase.mtime = currentTime;
                MedicalCaseManager.getInstance().addNewMedicalCase(getCaseFromView());
                MessageProcessor.getInstance().UpdateMedicalCase(medicalCase);
            }
        }

        private void submitButton_Click(object sender, EventArgs e)
        {
            if(medicalCase != null)
            {
                getCaseFromView();
                medicalCase.is_submit = "true";

                String currentTime = System.DateTime.Now.ToString();
                medicalCase.mtime = currentTime;

                updateMedicalCaseDirectory(new List<GynaecologyCase> { medicalCase });
                MedicalCaseManager.getInstance().addNewMedicalCase(getCaseFromView());
                MessageProcessor.getInstance().UpdateMedicalCase(medicalCase);
            }
        }
    }

    class ListViewItemComparer : IComparer
    {
          private int col;
          public int Compare(object x, object y)
          {
              int returnVal = -1;
              returnVal = String.Compare(((ListViewItem)x).SubItems[col].Text,
              ((ListViewItem)y).SubItems[col].Text);
              return returnVal;
          }
    }
    
}
