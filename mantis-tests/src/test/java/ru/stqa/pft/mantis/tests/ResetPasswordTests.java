package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().users().without(new UserData().withId(1)).size() == 0) {
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String password = "password";
            String email = String.format("user%s@localhost", now);
            app.james().createUser(user, password);
            app.registration().start(user, email);
        }
    }

    @Test
    public void testResetPassword() throws MessagingException, IOException {
        // получаем список пользователей mantis, исключая админа
        Users users = app.db().users().without(new UserData().withId(1));
        // выбираем пользователя, для смены пароля
        UserData userForReset = users.iterator().next();
        String username = userForReset.getUsername();
        String password = "password";
        String newPassword = "newpassword";
        // меняем пароль
        resetPassword(userForReset.getEmail(), username, password, newPassword);
        // проверяем возможность входа с новым паролем
        assertTrue(app.newSession().login(username, newPassword));
        // возвращаем пароль, для повторного теста
        resetPassword(userForReset.getEmail(), username, password, password);
    }

    private static void resetPassword(String email, String username, String mailPassword, String newPassword) throws MessagingException {
        // удаляем письма пользователя
        app.james().drainEmail(username, mailPassword);
        // инициируем смену пароля
        app.password().startReset(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), username);
        // получаем письмо, о смене пароля
        List<MailMessage> mailMessages = app.james().waitForMail(username, mailPassword, 60000);
        // находим ссылку, для смены пароля
        String resetLink = app.james().findLink(mailMessages, email);
        // меняем пароль
        app.password().finishReset(resetLink, newPassword);
    }
}
