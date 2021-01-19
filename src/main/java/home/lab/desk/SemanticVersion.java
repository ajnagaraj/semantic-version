package home.lab.desk;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation that represents semantic versions ignoring any additional labels.
 *
 * The class is designed to parse a given String and record the semantic numerical sections that map a major, minor
 * and a patch. The comparison is then done using those sections.
 */
public final class SemanticVersion implements Comparable<SemanticVersion> {
    private static final String DELIM_DOT = "\\.";
    private static final String DOT = ".";
    private static final int THREE_VERSION_PARTS = 3;
    private static final int ZERO_DEFAULT = 0;
    private static final int MAJOR_INDEX = 0;
    private static final int MINOR_INDEX = 1;
    private static final int PATCH_INDEX = 2;

    private final int major;
    private final int minor;
    private final int patch;

    private SemanticVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * A static factory that parses a given string and constructs a valid representation of semantic version with major, minor
     * and patch numerical sections
     *
     * @param version - a String that represents the semantic version [major.minor.patch]
     *
     * @return a new instance of SemanticVersion
     */
    public static SemanticVersion parse(String version) {
        if (version == null) {
            throw new IllegalArgumentException("Semantic version cannot be [null]");
        }

        String[] semVerSplit = version.split(DELIM_DOT);

        if (semVerSplit.length > THREE_VERSION_PARTS) {
            throw new IllegalArgumentException(String.format("Given [%s] semantic version is invalid",
                    version));
        }

        int major = getVersion(MAJOR_INDEX, version, semVerSplit, false);
        int minor = getVersion(MINOR_INDEX, version, semVerSplit, false);
        int patch = getVersion(PATCH_INDEX, version, semVerSplit, true);

        return new SemanticVersion(major, minor, patch);
    }

    /**
     * A static factory method that constructs a new instance of SemanticVersion using the given
     * major, minor and patch sections
     *
     * @param major the major version as an integer
     * @param minor the minor version an an integer
     * @param patch the patch as an integer
     *
     * @return new instance of SemanticVersion
     */
    public static SemanticVersion newInstance(int major, int minor, int patch) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException(String.format("Given [%s.%s.%s] semantic version is invalid",
                    major, minor, patch));
        }

        return new SemanticVersion(major, minor, patch);
    }

    private static int getVersion(int verIndex, String givenSemVer, String[] verSplit, boolean ignoreAlphaNumeric) {
        if (!(verSplit.length > verIndex)) {
            return ZERO_DEFAULT;
        }

        String version = verSplit[verIndex];

        Pattern pattern = (ignoreAlphaNumeric) ? Pattern.compile("^\\d+") : Pattern.compile("^\\d+$");
        Matcher matchForNumbers = pattern.matcher(version);
        if (!matchForNumbers.find()) {
            throw new IllegalArgumentException(String.format("Given [%s] semantic version is invalid", givenSemVer));
        }

        String greppedVersion = matchForNumbers.group();
        return Integer.parseInt(greppedVersion);
    }

    /**
     * Returns major version
     *
     * @return the major version
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns minor version
     *
     * @return the minor version
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Returns patch version
     *
     * @return the patch version
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Compares the major, minor and patch versions represented by two SemanticVersion instances
     *
     * @param otherSemVersion the SemanticVersion to be compared
     *
     * @return the value 0 if versions are the same; a value less than 0 if one of the major, minor and patch
     *         versions is less than the other SemanticVersion representation; a value greater than 0 if one of the major,
     *         minor or patch versions is greater than the other SemanticVersion representation
     */
    @Override
    public int compareTo(SemanticVersion otherSemVersion) {
        if (this.getMajor() == otherSemVersion.getMajor()) {
            if (this.getMinor() == otherSemVersion.getMinor()) {
                return Integer.compare(this.getPatch(), otherSemVersion.getPatch());
            } else {
                return Integer.compare(this.getMinor(), otherSemVersion.getMinor());
            }
        } else {
            return Integer.compare(this.getMajor(), otherSemVersion.getMajor());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SemanticVersion)) {
            return false;
        }

        SemanticVersion other = (SemanticVersion) o;

        return this.major == other.major
                && this.minor == other.minor
                && this.patch == other.patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getMajor())
                .append(DOT)
                .append(getMinor())
                .append(DOT)
                .append(getPatch())
                .toString();
    }
}
