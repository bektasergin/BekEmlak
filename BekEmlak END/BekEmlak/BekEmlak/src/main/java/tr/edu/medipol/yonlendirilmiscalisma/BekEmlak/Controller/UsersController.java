package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Controller;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.LoginDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.DTO.UsersDTO;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Entity.Users;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Repo.UsersRepository;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service.ForgotPasswordService;
import tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service.UsersService;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@Slf4j
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/selamla")
    public String selamla(){
        return "hello";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody @NotNull UsersDTO usersDTO){

        /*Bu kod kayıt olurken girdiğimiz emailin veritabanında başka
        bir hesapta kayıtlı olup olmadığını kontrol ediyor.*/
        if (usersService.isUserExists(usersDTO.getEmail())) {
           return "Bu e-posta adresi zaten kullanılıyor!";
        }

        /*Bu kod Usersdan bir nesne oluşuturuyor ve oluşturduğumuz nesnenin
        özellikleriyle dtodaki özellikleri eşliyor.*/
        Users users = new Users();

        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setName(usersDTO.getName());
        users.setSurname(usersDTO.getSurname());
        users.setEmail(usersDTO.getEmail());

        /*Kayıt olurken girdiğimiz bilgileri değişkene atıyor ve veri tabanına kayıt ediyor.*/
        Users registeredUser = usersService.addUser(users);

        return registeredUser.getName() + " " + registeredUser.getSurname() + " ismiyle yeni bir kullanıcı oluşturuldu!";
    }

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto, Model model) {
        /*loginDtonun email ve passwordunu
        email ve password diye yeni bir atama yapıyor*/
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        /*Girilen emaili araştırıyor kayıtlı mı değil mi diye*/
        Users user = usersRepository.findByUsername(username);

        model.addAttribute("userName", "Bektaş");

        /*Eğer girilen email ve şifre doğruysa "Giriş başarılı!" uyarısı veriyor,
        yanlışsa "Email ve Şifrenizi kontrol edin!" uyarısı veriyor*/
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Giriş başarılı!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ve Şifrenizi kontrol edin!");
        }
    }


    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgotPassword")
    /*Bu kod, bir kullanıcının şifresini unuttuğunu belirttiği bir e-posta adresini alarak,
    bu e-posta adresine şifre sıfırlama bağlantısı içeren bir e-posta gönderen bir hizmeti çağırıyor
    ve bu işlem sonucunda bir ModelAndView nesnesi döndürüyor.*/
    public ModelAndView forgotPassword(@RequestParam String email) throws MessagingException {
        return forgotPasswordService.sendForgotPasswordEmail(email);
    }



    @PostMapping("/ValidateOtp")
    public ModelAndView validateOtp(@RequestParam("otp") int value, HttpServletRequest request) {
        // Mevcut HTTP oturumunu alır.
        HttpSession session = request.getSession();

        // Oturumdan OTP (One-Time Password) ve e-posta adresini alır.
        Integer otpInteger = (Integer) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");

        // Eğer OTP değeri null değilse,
        // integer değere dönüştürür, aksi halde 0 olarak ayarlar.
        int otp = otpInteger != null ? otpInteger : 0;

        // Yeni bir ModelAndView nesnesi oluşturulur.
        ModelAndView modelAndView = new ModelAndView();

        // Girilen değer OTP ile eşleşirse...
        if (value == otp) {
            // Yönlendirme yapılacak sayfanın görünümünü ayarlar ve
            // başarılı bir durumda yeni bir şifre belirleme sayfasına yönlendirir.
            modelAndView.setViewName("redirect:/newPassword.html");
            // Yeni sayfaya e-posta adresini ve başarılı bir durumun mesajını ekler.
            modelAndView.addObject("email", email);
            modelAndView.addObject("status", "success");
        } else {
            // OTP ile eşleşmezse, hata sayfasına yönlendirir.
            modelAndView.setViewName("redirect:/ErrorPagee.html");
            // Hata mesajını ekler.
            modelAndView.addObject("message", "Girilen kod yanlış, lütfen tekrar deneyiniz!");
        }

        return modelAndView;
    }

    @Autowired
    private DataSource dataSource;


    @PostMapping("/newPassword")
    public ModelAndView resetPassword(HttpSession session,
                                      @RequestParam String password,
                                      @RequestParam String confPassword) {

        // Yeni bir ModelAndView nesnesi oluşturulur.
        ModelAndView modelAndView = new ModelAndView();

        // Girilen şifre ve şifre doğrulama alanları boş değilse ve şifreler eşleşiyorsa...
        if (password != null && confPassword != null && password.equals(confPassword)) {
            try (Connection connection = dataSource.getConnection()) {
                // Veritabanı bağlantısı alınır ve şifre güncelleme işlemi için bir PreparedStatement hazırlanır.
                PreparedStatement pst = connection.prepareStatement("UPDATE Users SET password = ? WHERE email = ?");
                // PreparedStatement parametreleri ayarlanır.
                pst.setString(1, password);
                pst.setString(2, (String) session.getAttribute("email"));

                // UPDATE sorgusu çalıştırılır ve etkilenen satır sayısı alınır.
                int rowCount = pst.executeUpdate();
                // Eğer en az bir satır etkilenmişse...
                if (rowCount > 0) {
                    // Başarılı bir şekilde şifre güncellendiği için giriş sayfasına yönlendirme yapılır.
                    modelAndView.setViewName("redirect:/loginPage.html");
                } else {
                    // Etkilenen satır yoksa, hata sayfasına yönlendirme yapılır.
                    modelAndView.setViewName("redirect:/ErrorPage.html");
                }
            } catch (SQLException e) {
                // Veritabanı hatası durumunda, hata sayfasına yönlendirme yapılır ve hata ekrana yazdırılır.
                e.printStackTrace();
                modelAndView.setViewName("redirect:/ErrorPage.html");
            }
        } else {
            // Şifreler eşleşmiyorsa veya herhangi bir alan boşsa, hata sayfasına yönlendirme yapılır.
            modelAndView.setViewName("redirect:/ErrorPage.html");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // Oturumdan kullanıcı adını al
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Model aracılığıyla kullanıcı adını HTML dosyasına gönder
        model.addAttribute("userName", userName);

        return "home"; // Bu, gösterilecek HTML dosyasının adıdır (örneğin, home.html)
    }
}










