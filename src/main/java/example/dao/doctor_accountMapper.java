package example.dao;

import example.pojo.Doctor_account;
import org.apache.ibatis.annotations.Param;

public interface Doctor_accountMapper {
    Doctor_account findByDoctorId(@Param(value="doctor_account_id")String doctor_account_id);
    void updateDoctorAccount(Doctor_account doctorAccount);
    void loginChangeStatus(Doctor_account doctorAccount);
    void insertDoctor(Doctor_account doctorAccount);

}
