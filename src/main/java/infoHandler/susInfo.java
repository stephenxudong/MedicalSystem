package infoHandler;

import java.util.HashSet;
import java.util.List;

public class susInfo {

    private static HashSet<String> doctorIDSet = new HashSet<>();

    public static HashSet<String> getDoctorIDSet()
    {
        return doctorIDSet;
    }

    //todo 请在前台发回问题在数据库中提交病历前调用此函数
    public static void AddId(List<String> doctorList)
    {
        synchronized (doctorIDSet){
            for(String doctorID : doctorList)
                doctorIDSet.add(doctorID);
        }
    }

    public static void AddId(String doctorID)
    {
        synchronized (doctorIDSet) {
            doctorIDSet.add(doctorID);
        }
    }
}
