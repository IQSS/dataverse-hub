// Alternative approach - map both old and new paths to the same endpoint
// This would go in your actual API controller, not the redirect controller

@RestController
public class InstallationController {

    // This maps BOTH old and new paths to the same method
    @RequestMapping(value = {"/api/installation", "/api/installations"})
    public ResponseEntity<String> getInstallations() {
        // Your actual implementation
        return ResponseEntity.ok("Installations data");
    }

    @RequestMapping(value = {"/api/installation/status", "/api/installations/status"})
    public ResponseEntity<String> getInstallationStatus() {
        // Your actual implementation
        return ResponseEntity.ok("Installation status");
    }

    @RequestMapping(value = {"/api/installation/metrics", "/api/installations/metrics"})
    public ResponseEntity<String> getInstallationMetrics() {
        // Your actual implementation
        return ResponseEntity.ok("Installation metrics");
    }
}
