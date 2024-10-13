import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;
    private String symptoms;
    private String description;
    private String prescriptionId;
    private static final String CSV_PATH = "data/appointments.csv";

    public Appointment(String appointmentId, String patientId, String doctorId, 
                       LocalDate appointmentDate, LocalTime appointmentTime, String status, 
                       String symptoms, String description, String prescriptionId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.symptoms = symptoms;
        this.description = description;
        this.prescriptionId = prescriptionId;
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

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
        updateCSV();
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
        updateCSV();
    }

    public void setStatus(String status) {
        this.status = status;
        updateCSV();
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
        updateCSV();
    }

    public void setDescription(String description) {
        this.description = description;
        updateCSV();
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Appointment> appointments = loadAppointments(CSV_PATH);
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).appointmentId.equals(this.appointmentId)) {
                appointments.set(i, this);
                break;
            }
        }
        saveAppointments(appointments, CSV_PATH);
    }

    private static void saveAppointments(ArrayList<Appointment> appointments, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("appointment_id,patient_id,doctor_id,appointment_date,appointment_time,status,symptoms,description,prescription_id\n");
            for (Appointment appointment : appointments) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,\"%s\",\"%s\",%s\n",
                    appointment.appointmentId, appointment.patientId, appointment.doctorId,
                    appointment.appointmentDate, appointment.appointmentTime, appointment.status,
                    appointment.symptoms, appointment.description, appointment.prescriptionId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Appointment> loadAppointments(String file_path) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Appointment appointment = new Appointment(
                    data[0], // appointmentId
                    data[1], // patientId
                    data[2], // doctorId
                    LocalDate.parse(data[3]), // appointmentDate
                    LocalTime.parse(data[4]), // appointmentTime
                    data[5], // status
                    data[6], // symptoms
                    data[7], // description
                    data[8]  // prescriptionId
                );
                appointments.add(appointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return appointments;
    }
}
