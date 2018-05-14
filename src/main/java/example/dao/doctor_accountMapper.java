package example.dao;

import example.pojo.doctor_account;
import org.apache.ibatis.annotations.Param;

public interface doctor_accountMapper {
    doctor_account loginResponse(@Param(value="userName")String name, @Param(value="userPassword")String area);
}
