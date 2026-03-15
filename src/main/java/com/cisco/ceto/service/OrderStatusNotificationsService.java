import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class OrderStatusNotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    /**
     * Sends real-time order status updates to customers via email and SMS.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    public void sendOrderStatusUpdate(String orderId, String status) {
        // TODO: Implement logic to retrieve customer details based on orderId
        String customerEmail = "customer@example.com"; // Placeholder
        String customerPhone = "1234567890"; // Placeholder

        sendEmailNotification(customerEmail, orderId, status);
        sendSmsNotification(customerPhone, orderId, status);

        // TODO: Implement logic to update customer portal in real-time
    }

    /**
     * Sends an email notification to the customer about the order status update.
     *
     * @param email   the customer's email address
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    private void sendEmailNotification(String email, String orderId, String status) {
        // TODO: Implement email sending logic
        emailService.sendEmail(email, "Order Status Update", "Your order " + orderId + " is now " + status);
    }

    /**
     * Sends an SMS notification to the customer about the order status update.
     *
     * @param phone   the customer's phone number
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    private void sendSmsNotification(String phone, String orderId, String status) {
        // TODO: Implement SMS sending logic
        smsService.sendSms(phone, "Your order " + orderId + " is now " + status);
    }
}

@RestController
@RequestMapping("/api/orders")
public class OrderStatusController {

    @Autowired
    private OrderStatusNotificationService notificationService;

    /**
     * Endpoint to update order status and trigger notifications.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     * @return a message indicating the success of the operation
     */
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        notificationService.sendOrderStatusUpdate(orderId, status);
        return "Order status updated and notifications sent";
    }
}

interface EmailService {
    void sendEmail(String to, String subject, String body);
}

interface SmsService {
    void sendSms(String to, String message);
}