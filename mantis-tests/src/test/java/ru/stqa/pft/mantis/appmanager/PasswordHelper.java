package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordHelper extends HelperBase {

    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void startReset(String adminLogin, String adminPassword, String resetUsername) {
        // заходим в mantis как admin
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), adminLogin);
        click(By.cssSelector("input[value='Вход']"));
        type(By.name("password"), adminPassword);
        click(By.cssSelector("input[value='Вход']"));
        // переходим на вкладку управление пользователями
        click(By.xpath("//span[contains(text(), 'Управление')]"));
        click(By.xpath("//a[contains(text(), 'Управление пользователями')]"));
        // выбираем пользователя для сброса пароля
        click(By.xpath(String.format("//a[contains(text(), '%s')]", resetUsername)));
        // нажимаем кнопку "Сбросить пароль"
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finishReset(String resetPasswordLink, String newPassword) {
        // переходим по ссылке для смены пароля и меняем его (скорее всего там такие же имена)
        wd.get(resetPasswordLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//button/span[contains(text(), 'Изменить пользователя')]"));
    }
}
