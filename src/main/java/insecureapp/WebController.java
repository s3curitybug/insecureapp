package insecureapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String getLogin() {

        return "login.jsp";

    }

    @PostMapping("/login")
    public String postLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String, Object> model) {

        String query = "select * from user where username = '" + username + "';";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next() && resultSet.getString("password").equals(password)) {

                String role = resultSet.getString("role");

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Arrays.asList(new SimpleGrantedAuthority(role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/admin";
                } else {
                    return "redirect:/";
                }

            }

        } catch (SQLException e) {
        }

        model.put("invalidCredentials", true);
        return "login.jsp";

    }

    @PostMapping("/register")
    public String postRegister(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String, Object> model) {

        String query = "select * from user where username = '" + username + "';";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                model.put("alreadyRegistered", true);
                return "login.jsp";
            }

        } catch (SQLException e) {
        }

        query = "insert into user values (null, '" + username + "', '" + password + "', 'ROLE_USER');";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
                Statement statement = connection.createStatement();) {

            statement.execute(query);

        } catch (SQLException e) {
        }

        return "redirect:/login";

    }

    @PostMapping("/logout")
    public String postLogout() {

        session.invalidate();
        return "redirect:/login";

    }

    @GetMapping("/")
    public String getIndex() {

        return "index.jsp";

    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(
            @RequestParam("filename") String filename) {

        return new FileSystemResource("/tmp/" + filename);

    }

    @GetMapping("/admin")
    public String getAdmin(
            Map<String, Object> model) {

        String query = "select * from user;";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            List<String> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(resultSet.getString("username"));
            }

            model.put("users", users);

        } catch (SQLException e) {
        }

        return "admin.jsp";

    }

    @PostMapping("/delete-user")
    public String deleteUser(
            @RequestParam("username") String username,
            Map<String, Object> model) {

        String query = "delete from user where username = '" + username + "';";

        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
                Statement statement = connection.createStatement()) {

            statement.execute(query);

        } catch (SQLException e) {
        }

        return "redirect:/admin";

    }

}
