package example.dao;

import example.pojo.medical_case;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface medical_caseMapper {
    List<medical_case> refreshDir(String doctor_account_id);
    List<medical_case> refreshContent(String patient_account_id);
    List<medical_case> selectByCaseId(String case_id);
    List<medical_case> checkAppState(@Param(value="doctor_account_id")String doctor_account_id, @Param(value="status")String status);
    void updateResponse(@Param(value="file_path")String file_path, @Param(value="patient_account_id")String patient_account_id,@Param(value="doctor_account_id")String doctor_account_id);
    void refreshReportStat(@Param(value="status")String status, @Param(value="case_id")String case_id);
}
