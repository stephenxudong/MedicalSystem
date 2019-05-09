package web.dao;

import web.pojo.Doctor_account;
import org.apache.ibatis.annotations.Param;

public interface Doctor_accountMapper {
    Doctor_account findByDoctorId(@Param(value = "doctor_account_id") String doctor_account_id);

    void updateDoctorAccount(Doctor_account doctorAccount);

    void loginChangeStatus(@Param(value = "doctor_account_id")String doctor_id,@Param(value = "status")String status);

    void insertDoctor(Doctor_account doctorAccount);

}