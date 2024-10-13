import java.util.ArrayList;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Doctor {

    private String doctorId;
    private String doctorName;
    private LocalDate doctorBirthday;
    private String doctorAddress;
    private String doctorContactNo;
    private String doctorQualification;
    private String doctorSpecialization;
    private LocalDate doctorRecruitedDate;
    private static final String CSV_PATH = "data/doctors.csv";

    // Constructor
    public Doctor(String doctorId, String doctorName, LocalDate doctorBirthday, String doctorAddress,
                  String doctorContactNo, String doctorQualification, String doctorSpecialization,
                  LocalDate doctorRecruitedDate) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorBirthday = doctorBirthday;
        this.doctorAddress = doctorAddress;
        this.doctorContactNo = doctorContactNo;
        this.doctorQualification = doctorQualification;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorRecruitedDate = doctorRecruitedDate;
    }

    // Setters
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
        updateCSV();
    }

    public void setDoctorBirthday(LocalDate doctorBirthday) {
        this.doctorBirthday = doctorBirthday;
        updateCSV();
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
        updateCSV();
    }

    public void setDoctorContactNo(String doctorContactNo) {
        this.doctorContactNo = doctorContactNo;
        updateCSV();
    }

    public void setDoctorQualification(String doctorQualification) {
        this.doctorQualification = doctorQualification;
        updateCSV();
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
        updateCSV();
    }

    public void setDoctorRecruitedDate(LocalDate doctorRecruitedDate) {
        this.doctorRecruitedDate = doctorRecruitedDate;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Doctor> doctors = loadDoctors(CSV_PATH);
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).doctorId.equals(this.doctorId)) {
                doctors.set(i, this);
                break;
            }
        }
        saveDoctors(doctors, CSV_PATH);
    }

    private static void saveDoctors(ArrayList<Doctor> doctors, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("doctor_id,doctor_name,doctor_birthday,doctor_address,doctor_contact_no,doctor_qualification,doctor_specialization,doctor_recruited_date\n");
            for (Doctor doctor : doctors) {
                writer.write(String.format("%s,%s,%s,\"%s\",\"%s\",\"%s\",\"%s\",%s\n",
                    doctor.doctorId, doctor.doctorName, doctor.doctorBirthday, doctor.doctorAddress,
                    doctor.doctorContactNo, doctor.doctorQualification, doctor.doctorSpecialization,
                    doctor.doctorRecruitedDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Doctor> loadDoctors(String file_path) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Doctor doctor = new Doctor(
                    data[0],
                    data[1],
                    LocalDate.parse(data[2]),
                    data[3].replace("\"", ""),
                    data[4].replace("\"", ""),
                    data[5].replace("\"", ""),
                    data[6].replace("\"", ""),
                    LocalDate.parse(data[7])
                );
                doctors.add(doctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return doctors;
    }

    // show doctor information
    @Override
    public String toString() {
        return String.format(
            "╔═══════════════════════════════════════════════════════════════════╗\n" +
            "║ 👨‍⚕️ Doctor Information                                             ║\n" +
            "╠═══════════════════════════════════════════════════════════════════╣\n" +
            "║ 🆔 Doctor ID:      %-48s ║\n" +
            "║ 👤 Name:           %-48s ║\n" +
            "║ 🎂 Birthday:       %-48s ║\n" +
            "║ 🏠 Address:        %-48s ║\n" +
            "║ ☎️  Contact No:     %-48s ║\n" +
            "║ 🎓 Qualification:  %-48s ║\n" +
            "║ 🏥 Specialization: %-48s ║\n" +
            "║ 📅 Recruited Date: %-48s ║\n" +
            "╚═══════════════════════════════════════════════════════════════════╝",
            doctorId, doctorName, doctorBirthday, doctorAddress, doctorContactNo,
            doctorQualification, doctorSpecialization, doctorRecruitedDate
        );
    }
}
