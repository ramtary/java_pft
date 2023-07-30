package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
    protected static final ApplicationManager app;

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        Issue issue = app.soap().getIssue(issueId);
        return !issue.getStatus().equals("resolved");
    }

    public void skipIfNotFixedBugify(int issueId) {
        if (isIssueOpenBugify(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpenBugify(int issueId) {
        Issue issue = app.rest().getIssue(issueId);
        return !issue.getStatus().equals("Resolved");
    }
}
