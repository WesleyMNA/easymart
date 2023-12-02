package com.ecommerce.catalog.services;

import com.ecommerce.catalog.models.Product;
import com.ecommerce.catalog.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductsInserter {

    private final ProductRepository repository;

    @EventListener
    public void addProducts(ContextRefreshedEvent event) {
        List<Product> products = getProducts();
        repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return List.of(
                new Product("Wireless Bluetooth Earbuds", 19.99f),
                new Product("Smart Fitness Tracker", 39.95f),
                new Product("Portable Espresso Maker", 79.99f),
                new Product("4K Ultra HD Smart TV", 149.99f),
                new Product("Professional DSLR Camera", 299.50f),
                new Product("Noise-Canceling Headphones", 129.95f),
                new Product("Compact Blender", 24.99f),
                new Product("Gaming Mouse with RGB Lighting", 79.50f),
                new Product("Solar-Powered Outdoor Lights", 199.00f),
                new Product("Stainless Steel Toaster", 45.99f),
                new Product("Home Theater Sound System", 349.99f),
                new Product("Wireless Charging Pad", 149.00f),
                new Product("Coffee Bean Grinder", 59.95f),
                new Product("Robot Vacuum Cleaner", 179.99f),
                new Product("Multi-Function Printer", 89.50f),
                new Product("Digital Air Fryer", 129.00f),
                new Product("Smart Home Security Camera", 299.99f),
                new Product("Ceramic Non-Stick Cookware Set", 19.95f),
                new Product("Fitness Yoga Mat", 69.99f),
                new Product("Wireless Gaming Headset", 54.50f),
                new Product("Smart Thermostat", 199.95f),
                new Product("Countertop Microwave Oven", 79.00f),
                new Product("LED Desk Lamp", 39.99f),
                new Product("Bluetooth Karaoke Microphone", 149.50f),
                new Product("Convertible Laptop/Tablet", 249.00f),
                new Product("Electric Toothbrush with UV Sanitizer", 29.95f),
                new Product("Wireless Charging Earbuds", 89.99f),
                new Product("Waterproof Outdoor Bluetooth Speaker", 179.50f),
                new Product("Memory Foam Neck Pillow", 34.99f),
                new Product("Cordless Handheld Vacuum Cleaner", 159.95f),
                new Product("4-Burner Gas Grill", 299.00f),
                new Product("HD Webcam with Microphone", 99.99f),
                new Product("Portable Power Bank", 69.95f),
                new Product("Wireless Router", 129.50f),
                new Product("Compact Digital Camera", 49.99f),
                new Product("Bluetooth Fitness Scale", 149.95f),
                new Product("Wireless Surround Sound System", 249.99f),
                new Product("Insulated Stainless Steel Water Bottle", 19.50f),
                new Product("Electric Wine Opener", 79.95f),
                new Product("Digital Drawing Tablet", 129.00f),
                new Product("Rechargeable Hand Warmer", 59.99f),
                new Product("Bluetooth Smart Lock", 199.50f),
                new Product("Mini Projector", 89.95f),
                new Product("Adjustable Standing Desk Converter", 119.99f),
                new Product("Wireless Gaming Controller", 34.95f),
                new Product("Automatic Pet Feeder", 149.50f),
                new Product("UV-C Light Sanitizer", 89.99f),
                new Product("Foldable Bluetooth Keyboard", 54.95f),
                new Product("Smart Coffee Maker", 199.00f),
                new Product("RGB Gaming Mouse Pad", 79.50f),
                new Product("Digital Photo Frame", 129.95f),
                new Product("Wireless Car Charger", 44.99f),
                new Product("4TB External Hard Drive", 299.00f),
                new Product("Portable Ice Maker", 69.50f),
                new Product("Collapsible Travel Backpack", 24.95f),
                new Product("In-Ear Monitor Headphones", 119.99f),
                new Product("Wi-Fi Range Extender", 179.00f),
                new Product("Aromatherapy Essential Oil Diffuser", 39.95f),
                new Product("Indoor Herb Garden Kit", 149.50f),
                new Product("Foldable Electric Scooter", 59.99f),
                new Product("Sleep Tracking Smart Pillow", 89.95f),
                new Product("Smart Water Bottle with Hydration Reminder", 199.50f),
                new Product("Digital Meat Thermometer", 79.99f),
                new Product("Wireless Mesh Router System", 299.00f),
                new Product("Mini Espresso Machine", 49.95f),
                new Product("Foldable Drone with HD Camera", 129.50f),
                new Product("Automatic Soap Dispenser", 69.99f),
                new Product("Fitness Resistance Bands Set", 159.95f),
                new Product("Bluetooth Beanie Hat", 34.50f),
                new Product("Collagen Protein Powder", 119.00f),
                new Product("Wireless Presenter Remote", 89.99f),
                new Product("Portable Handheld Fan", 44.95f),
                new Product("Smart Plug Set", 199.50f),
                new Product("Touchscreen Gloves", 79.95f),
                new Product("Digital Luggage Scale", 149.99f),
                new Product("Bluetooth FM Transmitter for Car", 59.50f),
                new Product("Smart Humidifier", 299.00f),
                new Product("LED Book Light", 39.99f),
                new Product("Digital Tire Inflator", 129.95f),
                new Product("Smart LED Light Bulbs", 69.50f),
                new Product("Portable Power Inverter", 179.99f),
                new Product("Adjustable Laptop Stand", 49.95f),
                new Product("Fitness Tracker Watch", 89.50f),
                new Product("Portable Hand Warmer Charger", 119.99f),
                new Product("Reusable Silicone Food Storage Bags", 24.50f),
                new Product("Bluetooth Barcode Scanner", 54.99f),
                new Product("Mini GPS Tracker", 199.00f),
                new Product("Wireless Charger Stand", 79.95f),
                new Product("Smart Doorbell Camera", 149.50f),
                new Product("Collapsible Silicone Travel Cup", 59.99f),
                new Product("Cord Organizer Kit", 34.95f)
        );
    }
}
