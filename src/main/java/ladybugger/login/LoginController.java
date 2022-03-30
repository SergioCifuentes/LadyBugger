package ladybugger.login;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/login")
public class LoginController {
    @CrossOrigin
    @PostMapping(produces="application/json")
    public @ResponseBody Login Login(@RequestBody Login login){
        System.out.println(login);
        return login;
    }

}
