package fi.hut.soberit.agilefant.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInitializer implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        if (jdbc.queryForInt("SELECT COUNT(*) FROM users") > 0) {
            log.info("Users table has data -> skipping initialization");
            return;
        }

        jdbc.update("INSERT INTO users (fullName, loginName, passwd, initials, enabled, recentItemsNumberOfWeeks) VALUES (?, ?, ?, ?, ?, ?)",
                "Administrator", "admin", "$2a$10$fkULKc1/AmruYR9HTmh5ROc5692D3WaPidZ7dHz073W33AvU2Vqom", "Admin", 1, 16);
        jdbc.update("INSERT INTO users (admin, fullName, loginName, passwd, initials, enabled, recentItemsNumberOfWeeks) VALUES (?, ?, ?, ?, ?, ?, ?)", 0,
                "readonly", "readonly", "$2a$10$fkULKc1/AmruYR9HTmh5ROc5692D3WaPidZ7dHz073W33AvU2Vqom", "readonly", 1, 0);

        log.info("Initialized users table with default users");

        if (jdbc.queryForInt("SELECT COUNT(*) FROM backlogs") > 0) {
            log.info("Backlogs table has data -> skipping initialization");
            return;
        }

        try {
            jdbc.update("INSERT INTO backlogs (description, name, parent_id, backlogtype) VALUES (?, ?, ?, ?)",
                "&nbsp;<br><b>Welcome to your Agilefant account!</b><br><br>This is a <b>Product. </b>Products<b>&nbsp;</b>are developed in <b>projects</b>, <br>which in turn can further be split into <b>iterations <br></b>(in Scrum, iterations are called \"sprints\").&nbsp;<br><br><div>On the <b>story tree</b>&nbsp;tab below you can see all the stories that <br>belong to this Product. You can create new stories directly<br> under the product, or create them in Project and<br> Iteration views and they will also be visible in this view. <br><br>On the <b>Project planning</b>&nbsp;tab, <br>you can drag n' drop the leaf stories (that is, stories that have <br>no children) into projects and iterations. And on the <b>projects <br></b>tab, you can create stories, set their status, assign people to <br>them and modify the projects' start and end dates.<br><div><br>If you want to get rid of all example data, just click Actions -&gt; Delete <br>for the Example Product of the backlogs.<br></div><div><br></div><div><u>For more info on Agilefant, see:</u><br></div><div>+ The <a href=\"http://agilefant.org/support/user-guide/\">User Guide</a></div><div>+ The <a href=\"http://agilefant.org/support/faq/\">FAQ</a> - details regarding specific features<br><br><u>For in-depth info on the principles behind Agilefant and their origin, see:</u><br>+ <a href=\"http://www.soberit.hut.fi/%7Ejvahanii/\">Dr. Agilefant's</a> Ph.D.: \"<a href=\"http://lib.tkk.fi/Diss/2012/isbn9789526045061/isbn9789526045061.pdf\">Towards Agile Product and Portfolio Management</a>\"<br></div></div>", "Example Product", null, "Product");

            int productId = jdbc.queryForInt("SELECT id FROM backlogs WHERE name='Example Product'");

            jdbc.update("INSERT INTO backlogs (description, name, parent_id, backlogSize, baselineLoad, endDate, rank, startDate, status, backlogtype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "&nbsp;<br>This is an example <b>project</b>.&nbsp;Think of&nbsp;Projects as corresponding <br>to a major release or an otherwise significant undertaking from a <br>business point of view. If you're familiar with Dean Leffingwell's <br><a href=\"http://scaledagileframework.com/\">scaled agile framework</a>, the project level in Agilefant corresponds to <br>\"<a href=\"http://www.scaledagileframework.com/program-level/\">program</a>\".<br><br>The <b>Story tree</b>&nbsp;tab works just like on the \nProduct level, with the <br>exception that it only displays those \nstories (and their parent stories) <br>that have been scheduled to be developed \nin this project.<br><br>On the&nbsp;<b>Leaf stories&nbsp;</b>tab, you can prioritize the leaf stories against <br>each other using a force-ranked list. You can also move them <br>into and out of <b>iterations</b>.<br><br>On the <b>iterations</b> tab you can see a single iteration. If you have<br>many teams working in your project, you'll want to create<br>an iteration for each of them.<div><br><div><div><b>Planned size</b> and <b>baseline load</b> are <br>explained in the <a href=\"http://agilefant.org/support/faq/\">FAQ</a>. Look at the <a href=\"http://agilefant.org/support/user-guide/\">user guide</a> to <br>see how the <b>project burn-up</b> works.</div></div></div>", "Example Project", productId, 0, 0, "2016-07-01", 0, "2015-07-01", 0, "Project");

            int projectId = jdbc.queryForInt("SELECT id FROM backlogs WHERE name='Example Project'");

            jdbc.update("INSERT INTO backlogs (description, name, parent_id, backlogSize, baselineLoad, endDate, readonlyToken, startDate, backlogtype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "&nbsp;<br>This is an iteration. Here you can see the <b>Stories</b> planned <br>for this iteration as a prioritised list.<br><br>Click the \"Create story\" button to create new stories. <br>Click the \"Actions\" button (on the top right corner of this Info area) to add <br>spent effort hours directly to the iteration, <br>or to save the Iteration data as an Excel file.<br><br>You can also have tasks that do not belong to any story.<br><br>(<b>Planned size</b> and <b>baseline load</b> are explained in the <a href=\"http://agilefant.org/support/faq/\">FAQ</a>.)", "Example Iteration", projectId, 0, 0, "2016-07-01", null, "2015-07-01", "Iteration");

            log.info("Initialized backlogs table with example backlogs");

            int iterationId = jdbc.queryForInt("SELECT id FROM backlogs WHERE name='Example Iteration'");

            jdbc.update("INSERT INTO stories (backlog_id, description, iteration_id, name, parent_id, state, storyPoints, storyValue, treeRank) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                projectId, "This is an example story.<br><br>You can create new stories from the upper left \"Create new\" menu or from \"Create story\" button in story list and story tree views.", iterationId, "Example Story", null, 0, null, null, 0);

            int storyId = jdbc.queryForInt("SELECT id FROM stories WHERE name='Example Story'");
            int userId = jdbc.queryForInt("SELECT id FROM users WHERE loginName='admin'");

            jdbc.update("INSERT INTO storyrank (backlog_id, rank, story_id) VALUES (?, ?, ?)",
                projectId, 0, storyId);

            jdbc.update("INSERT INTO storyrank (backlog_id, rank, story_id) VALUES (?, ?, ?)",
                iterationId, 0, storyId);

            jdbc.update("INSERT INTO story_user (Story_id, User_id) VALUES (?, ?)",
                storyId, userId);

            log.info("Initialized stories table with example story");

            jdbc.update("INSERT INTO teams (description, name) VALUES (?, ?)",
                "Example team for admin user", "Admin Team");

            int teamId = jdbc.queryForInt("SELECT id FROM teams WHERE name='Admin Team'");

            jdbc.update("INSERT INTO team_product (Team_id, Product_id) VALUES (?, ?)",
                teamId, productId);

            jdbc.update("INSERT INTO team_user (Team_id, User_id) VALUES (?, ?)",
                teamId, userId);

            log.info("Initialized team table with example team");

        } catch (Exception exception) {
            log.error("Failed to initialize backlogs: " + exception);
        }

    }

}
