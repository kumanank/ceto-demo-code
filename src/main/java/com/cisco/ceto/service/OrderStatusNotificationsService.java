import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class OrderStatusNotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private CustomerPortalService customerPortalService;

    /**
     * Sends real-time order status updates to customers via email and SMS.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    public void sendOrderStatusUpdate(String orderId, String status) {
        // TODO: Implement logic to send email notification
        emailService.sendEmailNotification(orderId, status);

        // TODO: Implement logic to send SMS notification
        smsService.sendSmsNotification(orderId, status);

        // TODO: Implement logic to update customer portal
        customerPortalService.updateOrderStatus(orderId, status);
    }
}

@RestController
@RequestMapping("/api/orders")
class OrderStatusController {

    @Autowired
    private OrderStatusNotificationService orderStatusNotificationService;

    /**
     * Endpoint to update order status and trigger notifications.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    @PutMapping("/{orderId}/status")
    public void updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        orderStatusNotificationService.sendOrderStatusUpdate(orderId, status);
    }
}

interface EmailService {
    /**
     * Sends an email notification to the customer.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    void sendEmailNotification(String orderId, String status);
}

interface SmsService {
    /**
     * Sends an SMS notification to the customer.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    void sendSmsNotification(String orderId, String status);
}

interface CustomerPortalService {
    /**
     * Updates the order status in the customer portal.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    void updateOrderStatus(String orderId, String status);
}