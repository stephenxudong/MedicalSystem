package web.dao;

import web.pojo.IdAndMtime;
import web.pojo.medicalCase.GynaecologyCase;

import java.util.List;

public interface GynaecologyCaseMapper {
    GynaecologyCase findByCaseId(String case_id);
    void insertGynaecologyCase(GynaecologyCase gynaecologyCase);
    //List<GynaecologyCase> checkAppState(@Param(value="doctor_account_id")String doctor_account_id, @Param(value="status")String status);
    //void updateResponse(@Param(value="file_path")String file_path, @Param(value="patient_account_id")String patient_account_id,@Param(value="doctor_account_id")String doctor_account_id);
    List<GynaecologyCase> selectGynaecologyCaseByDoctorId(String doctor_account_id);
    List<IdAndMtime> selectUndeliveredGynaecologyCase(String doctor_account_id);
    void updateGynaecologyCaseStatus(String case_id);
    List<IdAndMtime> selectIdAndMtime(String doctor_account_id);
    void updateGynaecologyCase(GynaecologyCase gynaecologyCase);
}
