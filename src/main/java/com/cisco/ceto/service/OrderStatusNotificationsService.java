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
    @Transactional
    public void sendOrderStatusUpdate(Long orderId, String status) {
        // TODO: Retrieve order details using orderId
        // TODO: Call emailService.sendEmail(orderDetails, status)
        // TODO: Call smsService.sendSms(orderDetails, status)
        // TODO: Call customerPortalService.updateStatus(orderId, status)
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
    public void updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        // TODO: Validate orderId and status
        orderStatusNotificationService.sendOrderStatusUpdate(orderId, status);
    }
}

interface EmailService {
    /**
     * Sends an email notification.
     *
     * @param orderDetails the order details
     * @param status       the new status of the order
     */
    void sendEmail(OrderDetails orderDetails, String status);
}

interface SmsService {
    /**
     * Sends an SMS notification.
     *
     * @param orderDetails the order details
     * @param status       the new status of the order
     */
    void sendSms(OrderDetails orderDetails, String status);
}

interface CustomerPortalService {
    /**
     * Updates the order status in the customer portal.
     *
     * @param orderId the ID of the order
     * @param status  the new status of the order
     */
    void updateStatus(Long orderId, String status);
}

class OrderDetails {
    // TODO: Define necessary fields and methods
}