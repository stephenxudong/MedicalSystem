package interaction.doctorIDTransfer;

import web.pojo.medicalCase.GynaecologyCase;

import java.util.Calendar;
import java.util.Date;

public class CheckDoctorIDImpl implements CheckDoctorID {

    private Date currentDate;
    private Date appointDate;
    private String doctorID;

    public CheckDoctorIDImpl(GynaecologyCase gynaecologyCase)
    {
        Calendar calendar = Calendar.getInstance();
        this.currentDate = calendar.getTime();
        this.appointDate = gynaecologyCase.getDate();
        this.doctorID = gynaecologyCase.getDoctor_account_id();
    }

    @Override
    public void CheckCurrentDoctorID()   //appointDate可以删除改为gynaecologyCase.getAppointDate
    {
        if(!(appointDate.after(currentDate))) {
            AddIDFunc.AddId(doctorID);
        }
    }
}
