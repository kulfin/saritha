package fi.hut.soberit.agilefant.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public final class AgilefantBuild {

    private static volatile AgilefantBuild BUILD = new AgilefantBuild("x.x.x", new Instant(), GitInformation.EMPTY, false);

    private final static String VERSION_PREFIX = "Implementation-Version: ";
    private final static String TIMESTAMP_PREFIX = "Agilefant-Build: ";

    public final String version;
    public final Instant buildTimestamp;
    public final GitInformation gitInformation;
    public final boolean releaseMode;

    private AgilefantBuild(String version, Instant buildTimestamp, GitInformation gitInformation, boolean releaseMode) {
        this.version = version;
        this.buildTimestamp = buildTimestamp;
        this.gitInformation = gitInformation;
        this.releaseMode = releaseMode;
    }

    public static AgilefantBuild getBuild() {
        return BUILD;
    }

    static void initialize(URL manifest) throws IOException {
        String version = "x.x.x-SNAPSHOT";
        Instant buildTimestamp = null;
        GitInformation gitInformation = GitInformation.EMPTY;

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd-HHmm");
        for (String line : Resources.readLines(manifest, Charsets.UTF_8)) {
            if (line.startsWith(VERSION_PREFIX)) {
                version = line.substring(VERSION_PREFIX.length());
            } else if (line.startsWith(TIMESTAMP_PREFIX)) {
                String value = line.substring(TIMESTAMP_PREFIX.length());
                try {
                    buildTimestamp = format.parseDateTime(value).toInstant();
                } catch (IllegalArgumentException e) {
                }
            }
        }

        URL gitProperties = AgilefantBuild.class.getClassLoader().getResource("git.properties");
        if (gitProperties != null) {
            Properties properties = new Properties();
            InputStream input = null;
            try {
                input = gitProperties.openStream();
                properties.load(input);

                gitInformation = new GitInformation(properties);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }

        boolean releaseMode = buildTimestamp != null;
        if (buildTimestamp == null)
            buildTimestamp = new Instant();

        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().append(ISODateTimeFormat.basicDate()).appendLiteral('-').appendHourOfDay(2)
                .appendMinuteOfHour(2).toFormatter();

        BUILD = new AgilefantBuild(version.replaceFirst("SNAPSHOT", buildTimestamp.toString(dateFormatter)), buildTimestamp, gitInformation, releaseMode);
    }

    public static class GitInformation {
        public final String branch;
        public final String describe;
        public final String commitId;
        public final String buildUserName;
        public final String buildUserEmail;
        public final String buildTime;
        public final String commitUserName;
        public final String commitUserEmail;
        public final String commitMessageShort;
        public final String commitMessageFull;
        public final String commitTime;

        public static final GitInformation EMPTY = new GitInformation("?", "?", "?", "?", "?", "?", "?", "?", "?", "?", "?");

        public GitInformation(String branch, String describe, String commitId, String buildUserName, String buildUserEmail, String buildTime,
                String commitUserName, String commitUserEmail, String commitMessageShort, String commitMessageFull, String commitTime) {
            this.branch = branch;
            this.describe = describe;
            this.commitId = commitId;
            this.buildUserName = buildUserName;
            this.buildUserEmail = buildUserEmail;
            this.buildTime = buildTime;
            this.commitUserName = commitUserName;
            this.commitUserEmail = commitUserEmail;
            this.commitMessageShort = commitMessageShort;
            this.commitMessageFull = commitMessageFull;
            this.commitTime = commitTime;
        }

        public GitInformation(Properties properties) {
            this(String.valueOf(properties.get("git.branch")), String.valueOf(properties.get("git.commit.id.describe")), String.valueOf(properties
                    .get("git.commit.id")), String.valueOf(properties.get("git.build.user.name")), String.valueOf(properties.get("git.build.user.email")),
                    String.valueOf(properties.get("git.build.time")), String.valueOf(properties.get("git.commit.user.name")), String.valueOf(properties
                            .get("git.commit.user.email")), String.valueOf(properties.get("git.commit.message.short")), String.valueOf(properties
                            .get("git.commit.message.full")), String.valueOf(properties.get("git.commit.time")));
        }
    }

}