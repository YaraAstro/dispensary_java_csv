import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Patient {

    private String patientId;
    private String patientName;
    private LocalDate patientBirthday;
    private String patientAddress;
    private String patientNIC;
    private String patientContactNo;
    private String patientBloodType;
    private String patientAllergies;
    private String patientDiseases;
    private double patientWeight;
    private double patientHeight;
    private static final String CSV_PATH = "data/patients.csv";

    // Constructor
    public Patient(String patientId, String patientName, LocalDate patientBirthday, String patientAddress,
                   String patientNIC, String patientContactNo, String patientBloodType, String patientAllergies,
                   String patientDiseases, double patientWeight, double patientHeight) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientBirthday = patientBirthday;
        this.patientAddress = patientAddress;
        this.patientNIC = patientNIC;
        this.patientContactNo = patientContactNo;
        this.patientBloodType = patientBloodType;
        this.patientAllergies = patientAllergies;
        this.patientDiseases = patientDiseases;
        this.patientWeight = patientWeight;
        this.patientHeight = patientHeight;
    }

    // Setters
    public void setPatientName(String patientName) {
        this.patientName = patientName;
        updateCSV();
    }

    public void setPatientBirthday(LocalDate patientBirthday) {
        this.patientBirthday = patientBirthday;
        updateCSV();
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
        updateCSV();
    }

    public void setPatientNIC(String patientNIC) {
        this.patientNIC = patientNIC;
        updateCSV();
    }

    public void setPatientContactNo(String patientContactNo) {
        this.patientContactNo = patientContactNo;
        updateCSV();
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
        updateCSV();
    }

    public void setPatientAllergies(String patientAllergies) {
        this.patientAllergies = patientAllergies;
        updateCSV();
    }

    public void setPatientDiseases(String patientDiseases) {
        this.patientDiseases = patientDiseases;
        updateCSV();
    }

    public void setPatientWeight(double patientWeight) {
        this.patientWeight = patientWeight;
        updateCSV();
    }

    public void setPatientHeight(double patientHeight) {
        this.patientHeight = patientHeight;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Patient> patients = loadPatients(CSV_PATH);
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).patientId.equals(this.patientId)) {
                patients.set(i, this);
                break;
            }
        }
        savePatients(patients, CSV_PATH);
    }

    private static void savePatients(ArrayList<Patient> patients, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("patient_id,patient_name,patient_birthday,patient_address,patient_NIC,patient_contact_no,patient_blood_type,patient_allergies,patient_diseases,patient_weight,patient_height\n");
            for (Patient patient : patients) {
                writer.write(String.format("%s,%s,%s,\"%s\",%s,%s,%s,%s,%s,%.1f,%.1f\n",
                    patient.patientId, patient.patientName, patient.patientBirthday, patient.patientAddress,
                    patient.patientNIC, patient.patientContactNo, patient.patientBloodType, patient.patientAllergies,
                    patient.patientDiseases, patient.patientWeight, patient.patientHeight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Patient> loadPatients(String file_path) {
        ArrayList<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Patient patient = new Patient(
                    data[0],
                    data[1],
                    LocalDate.parse(data[2]),
                    data[3].replace("\"", ""),
                    data[4],
                    data[5],
                    data[6],
                    data[7],
                    data[8],
                    Double.parseDouble(data[9]),
                    Double.parseDouble(data[10])
                );
                patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return patients;
    }

    // show patient information
    @Override
    public String toString() {
        return String.format(
            "╔═══════════════════════════════════════════════════════════════════╗\n" +
            "║ 👤 Patient Information                                            ║\n" +
            "╠═══════════════════════════════════════════════════════════════════╣\n" +
            "║ 🆔 Patient ID:     %-48s ║\n" +
            "║ 👤 Name:           %-48s ║\n" +
            "║ 🎂 Birthday:       %-48s ║\n" +
            "║ 🏠 Address:        %-48s ║\n" +
            "║ 📄 NIC:            %-48s ║\n" +
            "║ ☎️  Contact No:     %-48s ║\n" +
            "║ 🩸 Blood Type:     %-48s ║\n" +
            "║ 🚫 Allergies:      %-48s ║\n" +
            "║ 🏥 Diseases:       %-48s ║\n" +
            "║ ⚖️  Weight:         %-48s ║\n" +
            "║ 📏 Height:         %-48s ║\n" +
            "╚═══════════════════════════════════════════════════════════════════╝",
            patientId, patientName, patientBirthday, patientAddress, patientNIC,
            patientContactNo, patientBloodType, patientAllergies, patientDiseases,
            patientWeight + " kg", patientHeight + " cm"
        );
    }
}
