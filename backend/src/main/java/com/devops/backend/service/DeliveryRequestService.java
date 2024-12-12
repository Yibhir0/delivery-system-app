package com.devops.backend.service;

import com.devops.backend.form.DeliveryRequestForm;
import com.devops.backend.form.PackageForm;
import com.devops.backend.model.*;
import com.devops.backend.model.CustomType.*;
import com.devops.backend.model.Package.DeliveryPackage;
import com.devops.backend.repository.*;
import com.devops.backend.service.factory.PackageFactory;
import com.devops.backend.service.observer.NotificationObserver;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRequestService {
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final UserRepository userRepository;
    private final PackageFactory packageFactory;
    private final TrackerRepository trackerRepository;
    private final PackageRepository packageRepository;
    private final NotificationObserver notificationObserver;
    private final PaymentRepository paymentRepository;
    private final DriverRepository driverRepository;

    public DeliveryRequestService(DeliveryRequestRepository deliveryRequestRepository, UserRepository userRepository, PackageFactory packageFactory, TrackerRepository trackerRepository, PackageRepository packageRepository, NotificationObserver notificationObserver, PaymentRepository paymentRepository, DriverRepository driverRepository) {
        this.deliveryRequestRepository = deliveryRequestRepository;
        this.userRepository = userRepository;
        this.packageFactory = packageFactory;
        this.trackerRepository = trackerRepository;
        this.packageRepository = packageRepository;
        this.notificationObserver = notificationObserver;
        this.paymentRepository = paymentRepository;
        this.driverRepository = driverRepository;
    }

    @PostConstruct
    public void initialize() {
        addSampleClients();
        User alice = userRepository.findByEmail("alice@example.com");
        User bob = userRepository.findByEmail("bob@example.com");
        if (alice!=null && bob!=null) {
            List<DeliveryRequest> aliceRequests = deliveryRequestRepository.findByClientId(alice.getId());
            List<DeliveryRequest> bobRequests = deliveryRequestRepository.findByClientId(bob.getId());
            if(aliceRequests.isEmpty() || bobRequests.isEmpty()){
                addSampleDeliveryRequests(alice, bob);
            }
        }else{
            System.out.println("Sample clients not found.");
        }
    }

    private void addSampleClients() {
        // create 2 client Alice and Bob
        // check if the clients already exist
        User alice = userRepository.findByEmail("alice@example.com");
        User bob = userRepository.findByEmail("bob@example.com");
        if (alice==null && bob==null) {
            addClient("Alice", "Smith", "alice@example.com", "alice123");
            addClient("Bob", "Smith", "bob@example.com", "bob123");
        }
    }

    private void addClient(String firstName, String lastName, String email, String password) {
        Address address = new Address();
        address.setNumber(1455);
        address.setStreet("Boulevard de Maisonneuve");
        address.setCity("Montreal");
        address.setCountry("Canada");
        address.setZipCode("H3G 1M8");
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(UserType.CLIENT);
        user.setAddress(address);
        userRepository.save(user);
    }

    private void addSampleDeliveryRequests(User alice, User bob) {
        // Retrieve users Alice and Bob
        DeliveryRequest dr;

        // Create 5 delivery requests for Alice
        for (int i = 0; i < 5; i++) {
            dr = createSampleDeliveryRequest(alice, PackageType.values()[i % PackageType.values().length]);
            System.out.println("Delivery Request: " + dr.getId());

            // create payment
            Payment payment = dr.getPayment();
            payment.setAmount(dr.getCost());
            // if i is even set payment type to PAYPAL, else set it to CREDIT_CARD
            payment.setPaymentType(i % 2 == 0 ? PaymentType.PAYPAL : PaymentType.CREDIT_CARD);
            payment.setStatus(i % 2 == 0 ? PaymentStatus.COMPLETED : PaymentStatus.PENDING);
            payment.setPaymentDate(LocalDateTime.now());
            dr.setPayment(payment);
            paymentRepository.save(payment);

            // update the status of the delivery request
            Tracker tracker = dr.getPackageDetails().getTracker();
            tracker.addObserver(notificationObserver);
            tracker.updateTracker(PackageStatus.TO_BE_PICKED, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
            tracker.updateTracker(PackageStatus.PICKED_UP, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
            tracker.updateTracker(PackageStatus.IN_TRANSIT, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
            tracker.updateTracker(PackageStatus.READY_FOR_DELIVERY, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
            tracker.updateTracker(PackageStatus.OUT_FOR_DELIVERY, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
            tracker.updateTracker(PackageStatus.DELIVERED, alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
        }

        // Create 5 delivery requests for Bob
        for (int i = 0; i < 5; i++) {
            dr = createSampleDeliveryRequest(bob, PackageType.values()[i % PackageType.values().length]);
            // create payment
            Payment payment = dr.getPayment();
            payment.setAmount(dr.getCost());
            // if i is even set payment type to PAYPAL, else set it to CREDIT_CARD
            payment.setPaymentType(i % 2 == 0 ? PaymentType.PAYPAL : PaymentType.CREDIT_CARD);
            payment.setStatus(i % 2 == 0 ? PaymentStatus.COMPLETED : PaymentStatus.PENDING);
            payment.setPaymentDate(LocalDateTime.now());
            dr.setPayment(payment);
            paymentRepository.save(payment);
            // assign 2 delivery requests to a default driver
            if (i < 2) {
                assignDriver(dr);
            }
            // update the status of the delivery request
            Tracker tracker = dr.getPackageDetails().getTracker();
            tracker.addObserver(notificationObserver);
            tracker.updateTracker(PackageStatus.TO_BE_PICKED,alice.getAddress(), dr.getId());
            trackerRepository.save(tracker);
        }
    }

    private void assignDriver(DeliveryRequest dr) {
        dr.setAssigned(true);
        deliveryRequestRepository.save(dr);
        Driver driver = driverRepository.findAll().getFirst();
        driver.getPackages().add(dr);
        driverRepository.save(driver);
    }

    private DeliveryRequest createSampleDeliveryRequest(User user, PackageType type) {
        DeliveryRequestForm form = new DeliveryRequestForm();
        form.setClientId(user.getId());
        form.setSenderAddress(user.getAddress());
        form.setReceiverAddress(createSampleAddress());
        form.setPackageDetails(createSamplePackageDetails(type));
        return save(form);
    }

    private Address createSampleAddress() {
        Address address = new Address();
        address.setNumber(123);
        address.setStreet("Sample Street");
        address.setCity("Sample City");
        address.setCountry("Sample Country");
        address.setZipCode("12345");
        return address;
    }

    private PackageForm createSamplePackageDetails(PackageType type) {
        PackageForm details = new PackageForm();
        details.setPackageType(type);
        details.setWeight(1.0);
        details.setHeight(10.0);
        details.setWidth(10.0);
        details.setLength(10.0);
        return details;
    }

    // Retrieve all delivery requests
    public List<DeliveryRequest> getAllDeliveryRequests() {
        return deliveryRequestRepository.findAll();
    }

    // Retrieve a delivery request by ID
    public DeliveryRequest getDeliveryRequestById(Long id) {
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(id);
        return deliveryRequest.orElseThrow(() -> new IllegalArgumentException("Delivery request ID not valid"));
    }

    // Retrieve delivery requests by client ID
    public List<DeliveryRequest> getDeliveryRequestsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User ID not valid");
        }
        return deliveryRequestRepository.findByClientId(userId);
    }

    public DeliveryRequest save(DeliveryRequestForm deliveryRequestForm) {
        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setClient(userRepository.findById(deliveryRequestForm.getClientId()).orElseThrow(() -> new IllegalArgumentException("User ID not valid")));
        deliveryRequest.setSenderAddress(deliveryRequestForm.getSenderAddress());
        deliveryRequest.setReceiverAddress(deliveryRequestForm.getReceiverAddress());
        deliveryRequest.setCost(deliveryRequestForm.calculateCost());
        // add some payment placeholder
        Payment payment = new Payment();
        payment.setAmount(deliveryRequest.getCost());
        payment.setPaymentType(PaymentType.CREDIT_CARD);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        deliveryRequest.setPayment(payment);
        // create package
        DeliveryPackage deliveryPackage = packageFactory.createPackage(deliveryRequestForm.getPackageDetails().getPackageType());
        deliveryPackage.setWeight(deliveryRequestForm.getPackageDetails().getWeight());
        deliveryPackage.setHeight(deliveryRequestForm.getPackageDetails().getHeight());
        deliveryPackage.setWidth(deliveryRequestForm.getPackageDetails().getWidth());
        deliveryPackage.setLength(deliveryRequestForm.getPackageDetails().getLength());
        // create tracker
        Tracker tracker = new Tracker();
        tracker.setStatus(PackageStatus.CREATED);
        tracker.setLocation(deliveryRequestForm.getSenderAddress());
        tracker.addObserver(notificationObserver);
        deliveryPackage.setTracker(tracker);
        //packageRepository.save(deliveryPackage);
        deliveryRequest.setPackageDetails(deliveryPackage);
        return deliveryRequestRepository.save(deliveryRequest);
    }

    public DeliveryRequest updateSenderAddress(Long id, Address senderAddress) {
        // Retrieve the delivery request by ID
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(id);

        // Check if the delivery request exists
        if (deliveryRequest.isPresent()) {
            // Update the sender address
            DeliveryRequest updatedDeliveryRequest = deliveryRequest.get();
            updatedDeliveryRequest.setSenderAddress(senderAddress);

            // Save the updated delivery request to the repository
            return deliveryRequestRepository.save(updatedDeliveryRequest);
        } else {
            throw new IllegalArgumentException("Delivery request not found");
        }
    }

    public DeliveryRequest updateReceiverAddress(Long id, Address receiverAddress) {
        // Retrieve the delivery request by ID
        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(id);

        // Check if the delivery request exists
        if (deliveryRequest.isPresent()) {
            // Update the receiver address
            DeliveryRequest updatedDeliveryRequest = deliveryRequest.get();
            updatedDeliveryRequest.setReceiverAddress(receiverAddress);

            // Save the updated delivery request to the repository
            return deliveryRequestRepository.save(updatedDeliveryRequest);
        } else {
            throw new IllegalArgumentException("Delivery request not found");
        }
    }

    public void delete(Long id) {
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        deliveryRequestRepository.delete(deliveryRequest);
    }

    public List<DeliveryRequest> getUnassignedPackages() {
        // Retrieve all delivery requests that have not been assigned to a driver
        return deliveryRequestRepository.findByIsAssignedFalse();
    }
}
