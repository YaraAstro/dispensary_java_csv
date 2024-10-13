import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Prescription {
    
    private String prescriptionId;
    private String patientId;
    private String doctorId;
    private ArrayList<String> medicineIds;
    private ArrayList<String> dosages;
    private ArrayList<Integer> amounts;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    private double totalPrice;

    private static final String CSV_PATH = "data/prescriptions.csv";

    public Prescription(String prescriptionId, String patientId, String doctorId, 
                        ArrayList<String> medicineIds, ArrayList<String> dosages, 
                        ArrayList<Integer> amounts, String frequency, LocalDate startDate, 
                        LocalDate endDate, String notes, double totalPrice) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicineIds = medicineIds;
        this.dosages = dosages;
        this.amounts = amounts;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.totalPrice = totalPrice;
    }

    // Setters
    public void setPatientId(String patientId) {
        this.patientId = patientId;
        updateCSV();
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
        updateCSV();
    }

    public void setMedicineIds(ArrayList<String> medicineIds) {
        this.medicineIds = medicineIds;
        updateCSV();
    }

    public void setDosages(ArrayList<String> dosages) {
        this.dosages = dosages;
        updateCSV();
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
        updateCSV();
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
        updateCSV();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        updateCSV();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        updateCSV();
    }

    public void setNotes(String notes) {
        this.notes = notes;
        updateCSV();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Prescription> prescriptions = loadPrescriptions(CSV_PATH);
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).prescriptionId.equals(this.prescriptionId)) {
                prescriptions.set(i, this);
                break;
            }
        }
        savePrescriptions(prescriptions, CSV_PATH);
    }

    private static void savePrescriptions(ArrayList<Prescription> prescriptions, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("prescriptionId,patientId,doctorId,medicineIds,dosages,amounts,frequency,startDate,endDate,notes,totalPrice\n");
            for (Prescription prescription : prescriptions) {
                writer.write(String.format("%s,%s,%s,\"%s\",\"%s\",\"%s\",%s,%s,%s,\"%s\",%.2f\n",
                    prescription.prescriptionId, prescription.patientId, prescription.doctorId,
                    String.join(",", prescription.medicineIds),
                    String.join(",", prescription.dosages),
                    prescription.amounts.toString().replaceAll("\\[|\\]", ""),
                    prescription.frequency, prescription.startDate, prescription.endDate,
                    prescription.notes, prescription.totalPrice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Prescription> loadPrescriptions(String file_path) {
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ArrayList<String> medicineIds = new ArrayList<>(Arrays.asList(data[3].substring(1, data[3].length() - 1).split(",")));
                ArrayList<String> dosages = new ArrayList<>(Arrays.asList(data[4].substring(1, data[4].length() - 1).split(",")));
                ArrayList<Integer> amounts = new ArrayList<>();
                for (String amount : data[5].substring(1, data[5].length() - 1).split(",")) {
                    amounts.add(Integer.parseInt(amount));
                }
                Prescription prescription = new Prescription(
                    data[0], // prescriptionId
                    data[1], // patientId
                    data[2], // doctorId
                    medicineIds,
                    dosages,
                    amounts,
                    data[6], // frequency
                    LocalDate.parse(data[7]), // startDate
                    LocalDate.parse(data[8]), // endDate
                    data[9], // notes
                    Double.parseDouble(data[10]) // totalPrice
                );
                prescriptions.add(prescription);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return prescriptions;
    }
}
