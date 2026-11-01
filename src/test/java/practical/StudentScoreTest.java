package practical;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class StudentScoreTest {

    @Test
    public void analyzeStudentScores() {

        // Configure Chrome
        ChromeOptions options = new ChromeOptions();

        options.setBinary("/usr/bin/chromium");

        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Start ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        try {

            // Open the local web page
            driver.get("http://localhost:8000/students.html");

            // Locate the student table
            WebElement table = driver.findElement(By.id("studentTable"));

            // Find all rows inside the table body
            List<WebElement> rows =
                    table.findElements(By.cssSelector("tbody tr"));

            int totalStudents = rows.size();

            int studentsAbove60 = 0;

            System.out.println("========================================");
            System.out.println("       STUDENT SCORE ANALYSIS");
            System.out.println("========================================");

            // Process every student row
            for (WebElement row : rows) {

                // Get all cells in the current row
                List<WebElement> cells =
                        row.findElements(By.tagName("td"));

                String rollNo = cells.get(0).getText();
                String studentName = cells.get(1).getText();

                int english =
                        Integer.parseInt(cells.get(2).getText());

                int mathematics =
                        Integer.parseInt(cells.get(3).getText());

                int science =
                        Integer.parseInt(cells.get(4).getText());

                System.out.println("\nRoll No: " + rollNo);
                System.out.println("Name: " + studentName);
                System.out.println("English: " + english);
                System.out.println("Mathematics: " + mathematics);
                System.out.println("Science: " + science);

                //STUDENT TASK 1
              

            // Calculate percentage
            double percentage =
                    ((double) studentsAbove60 / totalStudents) * 100;

            System.out.println("\n========================================");
            System.out.println("          ANALYSIS RESULT");
            System.out.println("========================================");

            System.out.println(
                    "Total Students: " + totalStudents);

            System.out.println(
                    "Students scoring above 60 in at least one subject: "
                    + studentsAbove60);

            System.out.println(
                    "Percentage: " + percentage + "%");

            System.out.println("========================================");

        } finally {

            // Close browser
            driver.quit();
        }
    }
}