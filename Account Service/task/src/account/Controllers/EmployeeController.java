package account.Controllers;

import account.Entity.UserEntity;
import account.Servises.EmployeeService;
import account.Servises.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/empl")
@Validated
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/payment")
    public ResponseEntity<Object> payment(@AuthenticationPrincipal UserDetailImpl userDetails, @RequestParam(required = false) @Pattern(regexp = "(0[1-9]|1[0-2])-[1-2][0-1]\\d\\d") String period) {
        return employeeService.payment(userDetails, period);
    }
}
