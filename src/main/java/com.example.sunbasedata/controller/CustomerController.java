// ... (existing imports)

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final AuthService authService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(AuthService authService, CustomerService customerService) {
        this.authService = authService;
        this.customerService = customerService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticateUser(authRequest.getLoginId(), authRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization");
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        if (createdCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First Name or Last Name is missing");
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/customer/{uuid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String uuid) {
        if (customerService.deleteCustomer(uuid)) {
            return ResponseEntity.ok("Successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UUID not found");
        }
    }

    @PostMapping("/customer/{uuid}")
    public ResponseEntity<String> updateCustomer(@PathVariable String uuid, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(uuid, customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok("Successfully Updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Body is Empty or UUID not found");
        }
    }
}
