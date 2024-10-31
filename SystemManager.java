// class violates the Single Responsibility Principle, Open close Principle, Dependency inversion principle
// we need to refactor it,
// we can split the class into 4 classes, each class will have a single responsibility
// 1. OrderService
// 2. InventoryService
// 3. NotificationService
// 4. PaymentProcessor
// We have to adjust the methods according to their type of behavior. 
class SystemManager {
    constructor(inventoryService, paymentProcessor, orderService, notificationService) {
        this.inventoryService = inventoryService;
        this.paymentProcessor = paymentProcessor;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    //call proceso
    processOrder(order) {
        this.inventoryService.verify(order);
        this.paymentProcessor.process(order);
        this.orderService.updateStatus(order, "processed");
        this.notificationService.notify(order);
    }
}

class InventoryService {
    verify(order) {
        if (inventory < order.quantity) {
            throw new Error("Out of stock");
        }
    }
}

// The payment service violates principles Single Responsibility Principle, Open close Principle, Dependency inversion principle
class PaymentProcessor {
    process(order, priority = "normal") {
        // Implementar el procesamiento de pago
    }
}

class StandardPaymentProcessor extends PaymentProcessor {
    process(order) {
        if (paymentService.process(order.amount)) {
            return true;
        } else {
            throw new Error("Payment failed");
        }
    }
}

class ExpressPaymentProcessor extends PaymentProcessor {
    process(order, priority = "highPriority") {
        if (expressPaymentService.process(order.amount, priority)) {
            return true;
        } else {
            throw new Error("Express payment failed");
        }
    }
}


class OrderService {
    updateStatus(order, status) {
        database.updateOrderStatus(order.id, status);
    }
}


class NotificationService {
    notify(order) {
        emailService.sendEmail(order.customerEmail, "Your order has been processed.");
    }
}