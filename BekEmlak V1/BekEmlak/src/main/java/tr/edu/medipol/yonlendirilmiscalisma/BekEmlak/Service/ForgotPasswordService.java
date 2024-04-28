package tr.edu.medipol.yonlendirilmiscalisma.BekEmlak.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

//@Service
//public class ForgotPasswordService {
//
//    @Autowired
//    public JavaMailSender javaMailSender;
//
//    public ModelAndView sendForgotPasswordEmail(String email) throws MessagingException {
//        int otpvalue = 0;
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (email != null && !email.equals("")) {
//            Random rand = new Random();
//            otpvalue = rand.nextInt(1255650);
//
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//
//            helper.setFrom("erginbeko34@gmail.com");
//            helper.setTo(email);
//            helper.setSubject("Selamun Aleyküm");
//            helper.setText("your OTP is: " + otpvalue);
//            javaMailSender.send(message);
//            System.out.println("message sent successfully");
//
//            modelAndView.setViewName("redirect:/EnterOtp.html"); // Redirect to EnterOtp.html
//            modelAndView.addObject("message", "OTP is sent to your email id");
//        } else {
//            modelAndView.setViewName("redirect:/ErrorPage.html"); // Redirect to ErrorPage.html
//            modelAndView.addObject("errorMessage", "Email is required!");
//        }
//        return modelAndView;
//    }
//}
//
//
//
//
//
//


@Service
public class ForgotPasswordService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    private HttpServletRequest request;

    public ModelAndView sendForgotPasswordEmail(String email) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView();

        if (email != null && !email.equals("")) {
            Random rand = new Random();
            int otpvalue = rand.nextInt(1255650);

            HttpSession session = request.getSession();
            session.setAttribute("otp", otpvalue);
            session.setAttribute("email", email);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("erginbeko34@gmail.com");
            helper.setTo(email);
            helper.setSubject("Şifremi Unuttum");
            helper.setText("Şifre sıfırlama için OTP: " + otpvalue);
            javaMailSender.send(message);
            System.out.println("Message sent successfully");

            modelAndView.setViewName("redirect:/EnterOtp.html");
            modelAndView.addObject("message", "OTP e-posta adresinize gönderildi");
        } else {
            modelAndView.setViewName("redirect:/ErrorPage.html");
            modelAndView.addObject("errorMessage", "E-posta gereklidir!");
        }
        return modelAndView;
    }
}