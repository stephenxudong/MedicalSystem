package example.dao;

import example.pojo.doctor_account;
import org.apache.ibatis.annotations.Param;

public interface doctor_accountMapper {
    doctor_account loginResponse(@Param(value="doctor_account_id")String doctor_account_id, @Param(value="userPassword")String area);
}
