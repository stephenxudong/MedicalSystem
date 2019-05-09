package interaction.doctorIDTransfer;

import java.util.HashSet;

public class AddIDFunc {

    private static HashSet<String> doctorIDSet = new HashSet<>();
    public static HashSet<String> getDoctorIDSet()
    {
        return doctorIDSet;
    }

    public static void AddId(String doctorID)
    {
        synchronized (doctorIDSet) {
            doctorIDSet.add(doctorID);
        }
    }
}
