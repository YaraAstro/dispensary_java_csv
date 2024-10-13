import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private String appointmentId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String status;
    private static final String CSV_PATH = "data/payments.csv";

    public Payment(String paymentId, String appointmentId, double amount, LocalDate paymentDate, String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Setters
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
        updateCSV();
    }

    public void setAmount(double amount) {
        this.amount = amount;
        updateCSV();
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        updateCSV();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        updateCSV();
    }

    public void setStatus(String status) {
        this.status = status;
        updateCSV();
    }

    private void updateCSV() {
        ArrayList<Payment> payments = loadPayments(CSV_PATH);
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).paymentId.equals(this.paymentId)) {
                payments.set(i, this);
                break;
            }
        }
        savePayments(payments, CSV_PATH);
    }

    private static void savePayments(ArrayList<Payment> payments, String file_path) {
        try (FileWriter writer = new FileWriter(file_path)) {
            writer.write("payment_id,appointment_id,amount,payment_date,payment_method,status\n");
            for (Payment payment : payments) {
                writer.write(String.format("%s,%s,%.2f,%s,%s,%s\n",
                    payment.paymentId, payment.appointmentId, payment.amount,
                    payment.paymentDate, payment.paymentMethod, payment.status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Payment> loadPayments(String file_path) {
        ArrayList<Payment> payments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Payment payment = new Payment(
                    data[0], // paymentId
                    data[1], // appointmentId
                    Double.parseDouble(data[2]), // amount
                    LocalDate.parse(data[3]), // paymentDate
                    data[4], // paymentMethod
                    data[5]  // status
                );
                payments.add(payment);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return payments;
    }
}
